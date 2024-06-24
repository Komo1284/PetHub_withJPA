package pethub.with_JPA.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pethub.with_JPA.dto.MemberLoginDto;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.repository.MemberRepository;

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
}
