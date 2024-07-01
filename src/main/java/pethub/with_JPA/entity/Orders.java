package pethub.with_JPA.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Orders {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Embedded
    private Address delivery_address;

    private LocalDateTime order_date = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.ORDER;

    @PrePersist
    protected void onCreate() {
        if (order_date == null) {
            order_date = LocalDateTime.now();
        }
        if (orderStatus == null) {
            orderStatus = OrderStatus.ORDER;
        }
    }
}
