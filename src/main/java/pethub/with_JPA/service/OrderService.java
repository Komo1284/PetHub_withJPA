package pethub.with_JPA.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pethub.with_JPA.entity.Address;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.entity.OrderItem;
import pethub.with_JPA.entity.Orders;
import pethub.with_JPA.repository.MemberRepository;
import pethub.with_JPA.repository.OrderItemRepository;
import pethub.with_JPA.repository.OrdersRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final MemberRepository memberRepository;
    private final OrderItemRepository orderItemRepository;

    public Orders order(Long memberId, Address address, int totalPrice) {
        Member member = memberRepository.findById(memberId).get();
        Orders order = new Orders(member, address, totalPrice);
        Orders savedOrder = ordersRepository.save(order);

        List<OrderItem> byCart = orderItemRepository.findByCart(member.getCart());
        for (OrderItem orderItem : byCart) {
            orderItem.changeOrder(order);
        }

        savedOrder.changeCartToOrder(byCart);

        return savedOrder;

    }
}
