package pethub.with_JPA.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pethub.with_JPA.dto.AddCouponDto;
import pethub.with_JPA.entity.Coupon;
import pethub.with_JPA.repository.CouponRepository;
import pethub.with_JPA.service.CouponService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;
    private final CouponRepository couponRepository;

    @GetMapping("/newCoupon")
    public void adminNewCoupon() {}

    @PostMapping("/newCoupon")
    public ModelAndView adminNewCoupon(AddCouponDto dto) {
        ModelAndView mav = new ModelAndView("coupon/newCoupon");

        couponRepository.save(dto.setCoupon());

        mav.addObject("msg", "새로운 쿠폰이 성공적으로 추가되었습니다.");

        return mav;
    }

    @GetMapping("/issue_coupons/{id}")
    public ModelAndView adminIssueCoupons(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("member/myPage");

        couponService.issue_coupons(id);

        mav.addObject("msg", "쿠폰 발급에 성공하였습니다.");
        return mav;
    }

    @GetMapping("/del_coupon/{id}")
    public ModelAndView adminDelCoupon(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("member/myPage");

        Coupon coupon = couponRepository.findById(id).get();
        couponRepository.delete(coupon);

        mav.addObject("msg", "쿠폰 삭제에 성공하였습니다.");
        return mav;
    }
}
