package pethub.with_JPA.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * pethub.with_JPA.dto.QItemListDto is a Querydsl Projection type for ItemListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QItemListDto extends ConstructorExpression<ItemListDto> {

    private static final long serialVersionUID = -1419001237L;

    public QItemListDto(com.querydsl.core.types.Expression<Long> item_id, com.querydsl.core.types.Expression<String> itemProfile, com.querydsl.core.types.Expression<String> itemName, com.querydsl.core.types.Expression<Integer> price) {
        super(ItemListDto.class, new Class<?>[]{long.class, String.class, String.class, int.class}, item_id, itemProfile, itemName, price);
    }

}

