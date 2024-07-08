package pethub.with_JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pethub.with_JPA.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
