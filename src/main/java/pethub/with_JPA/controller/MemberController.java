package pethub.with_JPA.controller;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pethub.with_JPA.aop.PasswordEncoder;
import pethub.with_JPA.dto.MemberLoginDto;
import pethub.with_JPA.dto.SignUpDto;
import pethub.with_JPA.dto.UpdateMemberDto;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.repository.MemberRepository;
import pethub.with_JPA.service.EmailService;
import pethub.with_JPA.service.ImageService;
import pethub.with_JPA.service.MemberService;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final EmailService emailService;
    private final ImageService imageService;
    private final EntityManager entityManager;

    @GetMapping("/login")
    public void login() {
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @PostMapping("/login")
    public ModelAndView login(MemberLoginDto dto, HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/");
        session.setAttribute("user", memberService.login(dto));
        return mav;
    }

    @GetMapping("/signUp")
    public void signUp() {
    }

    @PostMapping("/signUp")
    public ModelAndView signUp(SignUpDto dto) {
        ModelAndView mav = new ModelAndView("member/signUp");
        mav.addObject("msg", memberService.signUp(dto));
        return mav;
    }

    @GetMapping("/myPage")
    public void myPage() {
    }

    @GetMapping("/update")
    public void update() {
    }

    @PostMapping("/update")
    public ModelAndView update(UpdateMemberDto dto, HttpSession session, MultipartFile file) throws IOException {
        Map<String, String> result = memberService.update(dto, session, file);
        ModelAndView mav = new ModelAndView(result.get("path"));
        mav.addObject("msg", result.get("msg"));
        return mav;
    }


    @PostMapping("/AuthNum")
    @ResponseBody
    ResponseEntity<?> sendAuthNum(@RequestBody Map<String, String> request, HttpSession session) throws MessagingException {

        String email = request.get("email");

        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("이메일을 입력해주세요.");
        }

        if (memberRepository.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("사용할 수 없는 이메일 입니다.");
        }

        // 랜덤 인증번호 발생 및 세션에 추가
        String random = UUID.randomUUID().toString().substring(0, 8);
        session.setAttribute("authNum", random);

        // 메일 발송
        emailService.sendAuthNum(email, random);

        return ResponseEntity.ok("인증번호가 전송되었습니다.");
    }

    // 회원가입시 중복아이디 체크를 위한 비동기통신 메서드
    @PostMapping("/checkUserId")
    public ResponseEntity<Map<String, Boolean>> checkUserId(@RequestBody Map<String, String> request) {
        String userid = request.get("userid");
        boolean exists = memberRepository.existsByUsername(userid);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/checkAuthNumUrl")
    public ResponseEntity<Map<String, Boolean>> checkAuthNumUrl(@RequestBody Map<String, String> request, HttpSession session) {
        String authNum = request.get("authNum");
        boolean exists = session.getAttribute("authNum").equals(authNum);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
}
