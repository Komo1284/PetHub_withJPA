package pethub.with_JPA.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * pethub.with_JPA.dto.QOrderListDto is a Querydsl Projection type for OrderListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrderListDto extends ConstructorExpression<OrderListDto> {

    private static final long serialVersionUID = -1941004234L;

    public QOrderListDto(com.querydsl.core.types.Expression<Long> order_id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> city, com.querydsl.core.types.Expression<String> street, com.querydsl.core.types.Expression<pethub.with_JPA.entity.OrderStatus> status, com.querydsl.core.types.Expression<? extends java.util.List<pethub.with_JPA.entity.OrderItem>> orderItems) {
        super(OrderListDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, pethub.with_JPA.entity.OrderStatus.class, java.util.List.class}, order_id, name, city, street, status, orderItems);
    }

}

