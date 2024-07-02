package pethub.with_JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pethub.with_JPA.dto.MemberLoginDto;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.entity.Role;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    List<Member> findByRole(Role role);

    Member findByUsername(String username);
}
