package pethub.with_JPA.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pethub.with_JPA.dto.MemberLoginDto;
import pethub.with_JPA.dto.SignUpDto;
import pethub.with_JPA.dto.UpdateMemberDto;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.repository.MemberRepository;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member login(MemberLoginDto member) {
        String username = member.getUsername();
        String password = member.getPassword();
        return memberRepository.findByUsernameAndPassword(username, password);
    }

    public String signUp(SignUpDto signUpDto) {
        if (memberRepository.existsByUsername(signUpDto.getUsername())){
            return "사용할 수 없는 아이디 입니다.";
        }

        if (!Objects.equals(signUpDto.getPassword(), signUpDto.getPwCheck())){
            return "입력하신 비밀번호가 서로 다릅니다.";
        }

        Member member = signUpDto.setMember(signUpDto);
        Member savedId = memberRepository.save(member);

        if (savedId.getId() != null) {
            return "회원가입이 완료되었습니다.";
        }
        return "회원가입에 실패하였습니다.";
    }

    public void update(UpdateMemberDto dto) {

    }
}
