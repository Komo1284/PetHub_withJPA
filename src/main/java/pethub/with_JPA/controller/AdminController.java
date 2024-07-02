package pethub.with_JPA.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.entity.Role;
import pethub.with_JPA.repository.MemberRepository;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MemberRepository memberRepository;

    @GetMapping("/insert")
    public void adminInsert() {}

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
    public void adminProductRegistration() {}

//    @PostMapping("/product_registration")
//    public ModelAndView adminProductRegistration(ItemVO item, MultipartFile file) throws IOException {
//        ModelAndView mav = new ModelAndView("admin/product_registration");
//        String msg;
//
//        if (item.getType() == 0) {
//            msg = "제품의 타입을 선택해주세요.";
//            mav.addObject("msg", msg);
//            return mav;
//        } else if (item.getCategory() == 0) {
//            msg = "제품의 카테고리를 선택해주세요.";
//            mav.addObject("msg", msg);
//            return mav;
//        }
//
//        item.setPic(is.imageUploadFromFile(file));
//        int result = as.AddProduct(item);
//        if (result == 1) {
//            msg = "상품등록에 성공하였습니다.";
//        } else {
//            msg = "상품등록중 에러가 발생하였습니다.";
//        }
//
//        mav.addObject("msg", msg);
//        return mav;

//    }
//    @GetMapping("/manage_orders/{id}")
//    public ModelAndView adminManageOrders(@PathVariable("id") int id) {
//        ModelAndView mav = new ModelAndView("admin/manage_orders");
//        String msg="주문 접수";
//
//        if(id ==2) msg="결제 완료";
//        else if (id == 3) msg="상품 준비중";
//        else if (id == 4) msg="주문 취소";
//        else if (id == 5) msg="반품 요청";
//        else if (id == 6) msg="반품 완료";
//        else if (id == 7) msg="배송중";
//        else if (id == 8) msg="배송완료";
//
//
//        mav.addObject("list", adminService.selectAll(msg));
//
//        return mav;

//    }
}
