package pethub.with_JPA.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pethub.with_JPA.aop.PasswordEncoder;
import pethub.with_JPA.dto.MemberLoginDto;
import pethub.with_JPA.dto.SignUpDto;
import pethub.with_JPA.dto.UpdateMemberDto;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.repository.MemberRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ImageService imageService;
    private final EntityManager entityManager;

    public Member login(MemberLoginDto dto) {
        String username = dto.getUsername();
        String hashPw = PasswordEncoder.encode(dto.getPassword());
        System.out.println("hashPw = " + hashPw);
        return memberRepository.findByUsernameAndPassword(username, hashPw);
    }

    public String signUp(SignUpDto dto) {
        if (memberRepository.existsByUsername(dto.getUsername())){
            return "사용할 수 없는 아이디 입니다.";
        }

        if (!Objects.equals(dto.getPassword(), dto.getPwCheck())){
            return "입력하신 비밀번호가 서로 다릅니다.";
        }

        String hashPw = PasswordEncoder.encode(dto.getPassword());
        dto.setPassword(hashPw);

        Member member = dto.setMember(dto);
        Member savedId = memberRepository.save(member);

        if (savedId.getId() != null) {
            return "회원가입이 완료되었습니다.";
        }
        return "회원가입에 실패하였습니다.";
    }

    public Map<String, String> update(UpdateMemberDto dto, HttpSession session, MultipartFile file) throws IOException {
        Member user = (Member) session.getAttribute("user");
        Map<String, String> result = new HashMap<>();
        result.put("path", "member/update");

        // 현재비밀번호가 일치하는지 확인
        String hashPw = dto.getPrevPw();
        hashPw = PasswordEncoder.encode(hashPw);

        if (!Objects.equals(user.getPassword(), hashPw)) {
            result.put("msg", "현재 비밀번호가 알맞지 않습니다.");
            return result;
        }

        // 변경할 비밀번호와 비밀번호체크가 동일하지 않다면 메세지를 담아서 수정페이지로 포워드
        if (!Objects.equals(dto.getNewPw(), dto.getNewPwCheck())) {
            result.put("msg", "변경할 비밀번호와 확인이 일치하지 않습니다.");
            return result;
        }

        String newHashedPw = PasswordEncoder.encode(dto.getNewPw());
        dto.setNewPw(newHashedPw);

        // 이미지를 s3 서버에 저장하여 저장된 이미지의 url을 세팅 - 이미지를 변경할 경우
        if (!file.isEmpty()) {
            String url = imageService.imageUploadFromFile(file);
            dto.setProfile(url);
        } else {
            dto.setProfile(user.getProfile());
        }

        try {
            Member findMember = memberRepository.findById(user.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Entity not found"));

            findMember.update(dto);
            entityManager.flush(); // 변경 사항을 즉시 데이터베이스에 반영

            result.put("msg", "수정이 완료되었습니다.");
            result.put("path", "redirect:/member/logout");
            return result;
        } catch (EntityNotFoundException e) {
            result.put("msg", "수정에 실패하였습니다: 엔티티를 찾을 수 없습니다.");
            return result;
        } catch (OptimisticLockException e) {
            result.put("msg", "수정에 실패하였습니다: 동시성 문제가 발생했습니다.");
            return result;
        } catch (PersistenceException e) {
            result.put("msg", "수정에 실패하였습니다: 데이터베이스 에러가 발생했습니다.");
            return result;
        } catch (Exception e) {
            result.put("msg", "수정에 실패하였습니다: 알 수 없는 에러가 발생했습니다.");
            return result;
        }
    }
}
