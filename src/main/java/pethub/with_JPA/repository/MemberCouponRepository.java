package pethub.with_JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pethub.with_JPA.entity.Coupon;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.entity.MemberCoupon;

import java.util.List;

@Repository
public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {

    List<MemberCoupon> findByMember(Member user);
}
