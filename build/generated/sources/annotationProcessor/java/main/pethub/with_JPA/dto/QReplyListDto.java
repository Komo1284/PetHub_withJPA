package pethub.with_JPA.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * pethub.with_JPA.dto.QReplyListDto is a Querydsl Projection type for ReplyListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReplyListDto extends ConstructorExpression<ReplyListDto> {

    private static final long serialVersionUID = 1816071450L;

    public QReplyListDto(com.querydsl.core.types.Expression<Long> board_id, com.querydsl.core.types.Expression<String> boardTitle, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<java.time.LocalDateTime> w_date) {
        super(ReplyListDto.class, new Class<?>[]{long.class, String.class, String.class, java.time.LocalDateTime.class}, board_id, boardTitle, content, w_date);
    }

}

