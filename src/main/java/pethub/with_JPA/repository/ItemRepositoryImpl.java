package pethub.with_JPA.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pethub.with_JPA.dto.*;
import pethub.with_JPA.entity.BoardType;
import pethub.with_JPA.entity.ItemCategory;
import pethub.with_JPA.entity.ItemType;

import java.util.List;

import static pethub.with_JPA.entity.QBoard.board;
import static pethub.with_JPA.entity.QItem.*;

@Repository
public class ItemRepositoryImpl implements  ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ItemListDto> searchItems(ItemSearchCondition condition, Pageable pageable) {
        List<ItemListDto> content = queryFactory
                .select(new QItemListDto(item.id, item.itemProfile, item.itemName, item.price))
                .from(item)
                .where(
                        selectItemType(condition),
                        selectItemCategory(condition),
                        itemSearch(condition)
                )
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(item.count())
                .from(item)
                .where(
                        selectItemType(condition),
                        selectItemCategory(condition),
                        itemSearch(condition)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression selectItemType(ItemSearchCondition condition) {
        if (condition.getItemType() != null) {
            switch (condition.getItemType()) {
                case CAT:
                    return item.itemType.eq(ItemType.CAT);
                case DOG:
                    return item.itemType.eq(ItemType.DOG);
                case BIRD:
                    return item.itemType.eq(ItemType.BIRD);
                case ETC:
                    return item.itemType.eq(ItemType.ETC);
                case FISH:
                    return item.itemType.eq(ItemType.FISH);
                default:
                    break;
            }
        }
        // 기본적으로 모든 타입을 포함하도록 설정
        return item.itemType.in(ItemType.CAT, ItemType.ETC, ItemType.FISH, ItemType.DOG, ItemType.BIRD);
    }

    private BooleanExpression selectItemCategory(ItemSearchCondition condition) {
        if (condition.getItemCategory() != null) {
            switch (condition.getItemCategory()) {
                case FOOD:
                    return item.itemCategory.eq(ItemCategory.FOOD);
                case SNACK:
                    return item.itemCategory.eq(ItemCategory.SNACK);
                case CLEAN:
                    return item.itemCategory.eq(ItemCategory.CLEAN);
                case BEAUTY:
                    return item.itemCategory.eq(ItemCategory.BEAUTY);
                case TOY:
                    return item.itemCategory.eq(ItemCategory.TOY);
                case HOUSING:
                    return item.itemCategory.eq(ItemCategory.HOUSING);
                case ETC:
                    return item.itemCategory.eq(ItemCategory.ETC);
                default:
                    break;
            }
        }
        // 기본적으로 모든 타입을 포함하도록 설정
        return item.itemCategory.in(ItemCategory.FOOD, ItemCategory.SNACK, ItemCategory.CLEAN,
                ItemCategory.BEAUTY, ItemCategory.TOY, ItemCategory.HOUSING, ItemCategory.ETC);
    }

    private BooleanExpression itemSearch(ItemSearchCondition condition) {
        if (condition.getSearchValue() == null) {
            return null;
        }
        return item.itemName.contains(condition.getSearchValue());
    }
}
