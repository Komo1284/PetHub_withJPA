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

    public void addItem(Long itemId, Long memberId, int quantity) {
        Item item = itemRepository.findById(itemId).get();
        Member member = memberRepository.findById(memberId).get();

        Cart cart = new Cart();
        member.setCart(cart);

        entityManager.flush();
        entityManager.clear();

        OrderItem orderItem = new OrderItem(item, quantity);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        savedOrderItem.setCartEach(member.getCart());
    }

    public List<OrderItem> showCart(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        return member.getCart().getOrderItems();
    }
}
