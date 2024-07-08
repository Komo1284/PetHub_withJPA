package pethub.with_JPA.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pethub.with_JPA.entity.Cart;
import pethub.with_JPA.entity.Item;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.entity.OrderItem;
import pethub.with_JPA.repository.CartRepository;
import pethub.with_JPA.repository.ItemRepository;
import pethub.with_JPA.repository.MemberRepository;
import pethub.with_JPA.repository.OrderItemRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final OrderItemRepository orderItemRepository;
    private final EntityManager entityManager;

    public String addItem(Long itemId, Long memberId, int quantity) {

        Item item = itemRepository.findById(itemId).get();
        Member member = memberRepository.findById(memberId).get();

        List<OrderItem> byCart = orderItemRepository.findByCart(member.getCart());
        for (OrderItem orderItem : byCart) {
            if (orderItem.getItem().getId() == itemId){
                return "redirect:/order/cart";
            }
        }

        OrderItem orderItem = new OrderItem(item, quantity);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        savedOrderItem.setCartEach(member.getCart());
        return "redirect:/item/detailPage/" + itemId;
    }

    public List<OrderItem> showCart(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        return member.getCart().getOrderItems();
    }

    public void buyItem(Long itemId, Long memberId, int quantity) {

        Item item = itemRepository.findById(itemId).get();
        Member member = memberRepository.findById(memberId).get();

        OrderItem orderItem = new OrderItem(item, quantity);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        savedOrderItem.setCartEach(member.getCart());
    }
}
