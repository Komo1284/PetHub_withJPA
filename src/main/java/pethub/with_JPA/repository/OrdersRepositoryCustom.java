package pethub.with_JPA.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pethub.with_JPA.dto.OrderListDto;
import pethub.with_JPA.dto.OrderSearchCondition;
import pethub.with_JPA.entity.OrderStatus;
import pethub.with_JPA.entity.Orders;

public interface OrdersRepositoryCustom {
    Page<Orders> searchOrders(String status, Pageable pageable);
}
