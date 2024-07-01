package pethub.with_JPA.dto;

import lombok.Data;
import pethub.with_JPA.entity.Coupon;

@Data
public class AddCouponDto {

    private String code;
    private int discount;
    private int min_price;
    private int discount_limit;

    public Coupon setCoupon() {
        return new Coupon(
                code, discount, min_price, discount
        );
    }
}
