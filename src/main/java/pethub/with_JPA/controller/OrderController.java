package pethub.with_JPA.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pethub.with_JPA.entity.Address;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.entity.Orders;
import pethub.with_JPA.repository.OrdersRepository;
import pethub.with_JPA.service.CartService;
import pethub.with_JPA.service.OrderService;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private final OrdersRepository ordersRepository;

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

    @GetMapping("/afterPay")
    public String afterPay(Model model, HttpSession session) {
        Member user = (Member) session.getAttribute("user");
        model.addAttribute("orders", ordersRepository.findByMember(user));
        return "order/afterPay";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Orders orders = ordersRepository.findById(id).get();
        ordersRepository.delete(orders);
        return "redirect:/order/afterPay";
    }
}
