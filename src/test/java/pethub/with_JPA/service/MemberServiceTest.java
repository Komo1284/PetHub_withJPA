package pethub.with_JPA.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pethub.with_JPA.dto.MemberLoginDto;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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
//        System.out.println("login = " + login);
//        System.out.println("member = " + member);
    }
}