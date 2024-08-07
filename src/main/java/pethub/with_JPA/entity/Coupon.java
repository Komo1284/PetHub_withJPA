package pethub.with_JPA.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Coupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String code;
    private int discount;
    private int min_price;
    private int discount_limit;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberCoupon> memberCoupons = new ArrayList<>();

    public Coupon(String code, int discount, int min_price, int discount_limit) {
        this.code = code;
        this.discount = discount;
        this.min_price = min_price;
        this.discount_limit = discount_limit;
    }
}
