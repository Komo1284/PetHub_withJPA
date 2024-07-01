package pethub.with_JPA.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pethub.with_JPA.entity.Coupon;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.entity.MemberCoupon;
import pethub.with_JPA.repository.CouponRepository;
import pethub.with_JPA.repository.MemberCouponRepository;
import pethub.with_JPA.repository.MemberRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;
    private final MemberCouponRepository memberCouponRepository;

    public void issue_coupons(Long coupon_id) {
        List<Member> list = memberRepository.findAll();
        Coupon coupon = couponRepository.findById(coupon_id).get();
        for (Member m : list) {
            MemberCoupon memberCoupon = new MemberCoupon(m, coupon);
            memberCouponRepository.save(memberCoupon);
        }
    }
}
