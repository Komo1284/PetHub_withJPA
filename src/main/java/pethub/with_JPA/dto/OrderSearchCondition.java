package pethub.with_JPA.dto;

import lombok.Data;
import pethub.with_JPA.entity.OrderStatus;

@Data
public class OrderSearchCondition {

    private OrderStatus status;
}
