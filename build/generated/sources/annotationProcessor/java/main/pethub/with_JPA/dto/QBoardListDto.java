package pethub.with_JPA.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * pethub.with_JPA.dto.QBoardListDto is a Querydsl Projection type for BoardListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBoardListDto extends ConstructorExpression<BoardListDto> {

    private static final long serialVersionUID = 1709702558L;

    public QBoardListDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> nick, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<java.time.LocalDateTime> w_date, com.querydsl.core.types.Expression<Integer> v_count) {
        super(BoardListDto.class, new Class<?>[]{long.class, String.class, String.class, java.time.LocalDateTime.class, int.class}, id, nick, title, w_date, v_count);
    }

}

