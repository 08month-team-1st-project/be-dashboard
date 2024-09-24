package project_1st_team03.dashboard.domain.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;
import project_1st_team03.dashboard.domain.member.domain.Member;

import java.util.Optional;

@RestController
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
