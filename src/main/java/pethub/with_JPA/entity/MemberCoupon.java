package pethub.with_JPA.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class MemberCoupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_coupon_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    private LocalDateTime regDate;
    private LocalDateTime endDate;

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
        coupon.getMemberCoupons().add(this);
    }

    @PrePersist
    public void prePersist() {
        this.regDate = LocalDateTime.now();
        this.endDate = this.regDate.plusMonths(1);
    }

    public MemberCoupon(Member member, Coupon coupon) {
        this.member = member;
        this.coupon = coupon;
    }
}
