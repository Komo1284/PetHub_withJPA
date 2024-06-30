package pethub.with_JPA.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pethub.with_JPA.dto.MemberLoginDto;
import pethub.with_JPA.dto.SignUpDto;
import pethub.with_JPA.dto.UpdateMemberDto;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.repository.MemberRepository;
import pethub.with_JPA.service.EmailService;
import pethub.with_JPA.service.MemberService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final EmailService emailService;

    @GetMapping("/login")
    public void login() {}

    @PostMapping("/login")
    public ModelAndView login(MemberLoginDto dto, HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/");
        session.setAttribute("user", memberService.login(dto));
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

    @PostMapping("/AuthNum")
    @ResponseBody
    ResponseEntity<?> sendAuthNum(@RequestBody Map<String, String> request, HttpSession session) throws MessagingException {

        String email = request.get("email");

        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("이메일을 입력해주세요.");
        }

        if (memberRepository.existsByEmail(email)){
            return ResponseEntity.badRequest().body("사용할 수 없는 이메일 입니다.");
        }

        // 랜덤 인증번호 발생 및 세션에 추가
        String random = UUID.randomUUID().toString().substring(0, 8);
        session.setAttribute("authNum", random);

        // 메일 발송
        emailService.sendAuthNum(email,random);

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
