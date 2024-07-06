package pethub.with_JPA.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pethub.with_JPA.entity.Cart;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.service.CartService;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

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
        cartService.addItem(item_id, user.getId(), quantity);
        model.addAttribute("msg", "장바구니에 추가 되었습니다.");
        return "redirect:/item/detailPage/" + item_id;
    }


}
