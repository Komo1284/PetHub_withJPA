package pethub.with_JPA.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pethub.with_JPA.dto.MemberLoginDto;
import pethub.with_JPA.dto.SignUpDto;
import pethub.with_JPA.dto.UpdateMemberDto;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.service.MemberService;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public void login() {}

    @PostMapping("/login")
    public ModelAndView login(MemberLoginDto dto) {
        ModelAndView mav = new ModelAndView("redirect:/");
        mav.addObject("login", memberService.login(dto));
        return mav;
    }

    @GetMapping("/signUp")
    public void signUp() {}

    @PostMapping("/signUp")
    public ModelAndView signUp(SignUpDto dto) {
        ModelAndView mav = new ModelAndView("member/signUp");
        mav.addObject("msg", memberService.signUp(dto));
        return mav;
    }

    @GetMapping("/myPage")
    public void myPage() {}

    @GetMapping("/update")
    public void update() {}

    @PostMapping("/update")
    public void update(UpdateMemberDto dto, MultipartFile file) {
        // MultipartFile S3서버에 저장후 반환된 URL을 dto에 저장
        // ~~~
        dto.setProfile(file.getOriginalFilename());

        memberService.update(dto);
    }
}
