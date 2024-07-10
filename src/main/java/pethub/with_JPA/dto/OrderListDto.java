package pethub.with_JPA.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import pethub.with_JPA.entity.OrderItem;
import pethub.with_JPA.entity.OrderStatus;

import java.util.List;

@Data
public class OrderListDto {

    private Long order_id;
    private String name;
    private String city;
    private String street;
    private OrderStatus status;
    private List<OrderItem> orderItems;

    @QueryProjection
    public OrderListDto(Long order_id, String name, String city, String street, OrderStatus status, List<OrderItem> orderItems) {
        this.order_id = order_id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.status = status;
        this.orderItems = orderItems;
    }
}
