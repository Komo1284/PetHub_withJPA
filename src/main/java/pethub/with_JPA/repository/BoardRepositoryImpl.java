package pethub.with_JPA.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pethub.with_JPA.dto.BoardListDto;
import pethub.with_JPA.dto.BoardSearchCondition;
import pethub.with_JPA.dto.QBoardListDto;
import pethub.with_JPA.entity.BoardType;

import java.util.List;

import static pethub.with_JPA.entity.QBoard.*;

@Repository
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<BoardListDto> searchBoards(BoardSearchCondition condition, Pageable pageable) {
        List<BoardListDto> content = queryFactory
                .select(new QBoardListDto(board.id, board.member.nick, board.title,
                        board.w_date, board.v_count))
                .from(board)
                .where(
                        selectBoardType(condition),
                        dynamicSearch(condition)
                )
                .orderBy(board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(board.count())
                .from(board)
                .where(
                        selectBoardType(condition),
                        dynamicSearch(condition)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression selectBoardType(BoardSearchCondition condition) {
        if (condition.getBoardType() != null) {
            switch (condition.getBoardType()) {
                case CAT:
                    return board.type.eq(BoardType.CAT);
                case DOG:
                    return board.type.eq(BoardType.DOG);
                case BIRD:
                    return board.type.eq(BoardType.BIRD);
                case ETC:
                    return board.type.eq(BoardType.ETC);
                case NOTICE:
                    return board.type.eq(BoardType.NOTICE);
                case FREE:
                    return board.type.eq(BoardType.FREE);
                default:
                    break;
            }
        }
        // 기본적으로 모든 타입을 포함하도록 설정
        return board.type.in(BoardType.CAT, BoardType.DOG, BoardType.BIRD, BoardType.ETC, BoardType.NOTICE, BoardType.FREE);
    }

    private BooleanExpression dynamicSearch(BoardSearchCondition condition) {
        if (condition.getSearchType() == null || condition.getSearchValue() == null) {
            return null;
        }
        return switch (condition.getSearchType()) {
            case "title" -> board.title.contains(condition.getSearchValue());
            case "nick" -> board.member.nick.contains(condition.getSearchValue());
            case "contents" -> board.content.contains(condition.getSearchValue());
            default -> null;
        };
    }
}
