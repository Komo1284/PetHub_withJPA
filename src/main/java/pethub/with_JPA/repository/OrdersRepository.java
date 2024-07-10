package pethub.with_JPA.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pethub.with_JPA.dto.OrderListDto;
import pethub.with_JPA.dto.OrderSearchCondition;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.entity.Orders;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>, OrdersRepositoryCustom {

    List<Orders> findByMember(Member user);

}
