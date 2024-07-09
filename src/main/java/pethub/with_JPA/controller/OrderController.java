package pethub.with_JPA.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pethub.with_JPA.entity.Address;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.entity.Orders;
import pethub.with_JPA.service.CartService;
import pethub.with_JPA.service.OrderService;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    @GetMapping
    public String order(Model model, HttpSession session) {
        Member user = (Member) session.getAttribute("user");
        model.addAttribute("orderItems", cartService.showCart(user.getId()));
        return "order/orderStatus";
    }

    @PostMapping
    public String order(Model model, String city, String street, String zipcode, int totalPrice, HttpSession session) {
        Member user = (Member) session.getAttribute("user");
        Address address = new Address(street, city, zipcode);
        Orders order = orderService.order(user.getId(), address, totalPrice);
        model.addAttribute("order", order);
        return "order/orderCheck";
    }
}
