package pethub.with_JPA.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pethub.with_JPA.dto.*;
import pethub.with_JPA.entity.OrderStatus;
import pethub.with_JPA.entity.Orders;

import java.util.List;

import static pethub.with_JPA.entity.QOrders.*;

@Repository
public class OrdersRepositoryImpl implements OrdersRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OrdersRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Orders> searchOrders(String status, Pageable pageable) {
        List<Orders> content = queryFactory
                .select(orders)
                .from(orders)
                .where(orders.orderStatus.eq(OrderStatus.valueOf(status)))
                .orderBy(orders.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(orders.count())
                .from(orders)
                .where(orders.orderStatus.eq(OrderStatus.valueOf(status)))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
