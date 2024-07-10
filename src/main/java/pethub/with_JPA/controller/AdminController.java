package pethub.with_JPA.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pethub.with_JPA.dto.*;
import pethub.with_JPA.entity.*;
import pethub.with_JPA.repository.MemberRepository;
import pethub.with_JPA.service.AdminService;
import pethub.with_JPA.service.ImageService;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MemberRepository memberRepository;
    private final ImageService imageService;
    private final AdminService adminService;

    @GetMapping("/insert")
    public void adminInsert() {
    }

    @PostMapping("/insert")
    @Transactional
    public ModelAndView adminInsert(@RequestParam String username) {
        ModelAndView mav = new ModelAndView("admin/insert");

        Member findMember = memberRepository.findByUsername(username);
        findMember.setRole(Role.ADMIN);

        mav.addObject("msg", "새로운 관리자가 성공적으로 추가되었습니다.");

        return mav;
    }

    @GetMapping("/delete/{id}")
    @Transactional
    public ModelAndView adminDelete(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("member/myPage");

        Member findMember = memberRepository.findById(id).get();
        findMember.setRole(Role.MEMBER);

        mav.addObject("msg", "성공적으로 관리자를 삭제하였습니다.");

        return mav;
    }

    @GetMapping("/product_registration")
    public void AddItem(Model model, @RequestParam(required = false) String msg) {
        model.addAttribute("itemTypes", ItemType.values());
        model.addAttribute("itemCategories", ItemCategory.values());
        if (msg != null) {
            model.addAttribute("msg", msg);
        }
    }

    @PostMapping("/product_registration")
    public ModelAndView AddItem(AddItemDto dto, MultipartFile file) throws IOException {
        ModelAndView mav = new ModelAndView();

        Map<String, String> result = adminService.AddProduct(dto, file);
        mav.addObject("msg", result.get("msg"));
        mav.setViewName(result.get("path"));
        return mav;
    }

    @GetMapping("/manage_orders")
    public String adminManageOrders(Model model,
                                    @RequestParam(required = false) String orderStatus,
                                    @PageableDefault(size = 8, page = 0) Pageable pageable) {

        Page<Orders> result = adminService.searchOrders(orderStatus, pageable);

        model.addAttribute("orderStatuses", OrderStatus.values());
        model.addAttribute("orders", result.getContent());
        model.addAttribute("page", result);
        model.addAttribute("status", orderStatus);

        return "admin/manage_orders";
    }
}
