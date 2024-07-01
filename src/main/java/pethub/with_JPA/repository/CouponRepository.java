package pethub.with_JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pethub.with_JPA.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
