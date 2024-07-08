package pethub.with_JPA.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pethub.with_JPA.entity.Cart;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.entity.OrderItem;
import pethub.with_JPA.repository.OrderItemRepository;
import pethub.with_JPA.service.CartService;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final OrderItemRepository orderItemRepository;

    @GetMapping("/order/cart")
    public String cart(Model model, HttpSession session) {
        Member user = (Member) session.getAttribute("user");
        model.addAttribute("orderItems", cartService.showCart(user.getId()));
        return "order/cart";
    }

    @PostMapping("/cart/add/{item_id}")
    public String addCart(@PathVariable Long item_id, Model model,
                          @RequestParam("quantity") int quantity, HttpSession session) {
        Member user = (Member) session.getAttribute("user");

        return cartService.addItem(item_id, user.getId(), quantity);
    }

    @PostMapping("/cart/buy/{item_id}")
    public String addCartAndBuy(@PathVariable Long item_id, Model model,
                          @RequestParam("quantity") int quantity, HttpSession session) {
        Member user = (Member) session.getAttribute("user");
        cartService.buyItem(item_id, user.getId(), quantity);
        return "redirect:/order/cart";
    }

    @Transactional
    @PostMapping("/cart/update/{id}")
    public String updateCart(@PathVariable Long id, @RequestParam("quantity") int quantity) {
        OrderItem orderItem = orderItemRepository.findById(id).get();
        orderItem.updateQuantity(quantity);
        return "redirect:/order/cart";
    }

    @GetMapping("/cart/delete/{id}")
    public String deleteCart(@PathVariable Long id) {
        orderItemRepository.delete(orderItemRepository.findById(id).get());
        return "redirect:/order/cart";
    }


}
