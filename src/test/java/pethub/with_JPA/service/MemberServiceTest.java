package pethub.with_JPA.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pethub.with_JPA.dto.MemberLoginDto;
import pethub.with_JPA.dto.SignUpDto;
import pethub.with_JPA.entity.Ad;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    public void loginTest() throws Exception {
        //given
        Member member = new Member("test", "1111");
        memberRepository.save(member);

        //when
        MemberLoginDto test = new MemberLoginDto("test", "1111");
        Member login = memberService.login(test);

        //then
        assertThat(login).isEqualTo(member);

    }

    @Test
    public void signUpTest() throws Exception {
        //given
        Member member = new Member("test", "1111");
        memberRepository.save(member);

        //when
        // 비밀번호 체크
        SignUpDto dto1 = new SignUpDto("test1", "1111", "2222", "테스트", "테스트1",
                "test@gamil.com", "부산", "동래", "44444", "01012341234", 1);
        // 아이디중복
        SignUpDto dto2 = new SignUpDto("test", "1111", "1111", "테스트", "테스트1",
                "test@gamil.com", "부산", "동래", "44444", "01012341234", 1);
        // 회원가입 성공
        SignUpDto dto3 = new SignUpDto("test2", "1111", "1111", "테스트", "테스트1",
                "test@gamil.com", "부산", "동래", "44444", "01012341234", 1);
        // 데이터 교차검증
        SignUpDto dto4 = new SignUpDto("test4", "1111", "1111", "테스트", "테스트1",
                "test@gamil.com", "부산", "동래", "44444", "01012341234", 1);

        String result1 = memberService.signUp(dto1);
        String result2 = memberService.signUp(dto2);
        String result3 = memberService.signUp(dto3);
        Member member1 = dto4.setMember(dto4);


        //then
        assertThat(result1).isEqualTo("입력하신 비밀번호가 서로 다릅니다.");
        assertThat(result2).isEqualTo("사용할 수 없는 아이디 입니다.");
        assertThat(result3).isEqualTo("회원가입이 완료되었습니다.");

        assertThat(member1.getUsername()).isEqualTo(dto4.getUsername());
        assertThat(member1.getPassword()).isEqualTo(dto4.getPassword());
        assertThat(member1.getEmail()).isEqualTo(dto4.getEmail());
        assertThat(member1.getPhone()).isEqualTo(dto4.getPhone());
        assertThat(member1.getAddress().getCity()).isEqualTo(dto4.getCity());
        assertThat(member1.getAddress().getStreet()).isEqualTo(dto4.getStreet());
        assertThat(member1.getAddress().getZipcode()).isEqualTo(dto4.getZipcode());
        assertThat(member1.getAd()).isEqualTo(Ad.ACCEPT);

    }
}