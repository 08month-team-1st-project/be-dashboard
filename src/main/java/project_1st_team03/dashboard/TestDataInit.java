package project_1st_team03.dashboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import project_1st_team03.dashboard.domain.member.dao.MemberRepository;
import project_1st_team03.dashboard.global.security.MemberRole;

import static project_1st_team03.dashboard.domain.member.domain.Member.createMember;

/**
 * Member 외 기능 초반 구현 용이하게 하려고 추가한 임시 클래스입니다. (Member 기능구현 후엔 삭제예정)
 * 구현하시면서 상황에 따라 주석처리하셔도 됩니다.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class TestDataInit {

    private final MemberRepository memberRepository;

    // 스프링 컨테이너가 초기화를 전부 끝내고, 실행 준비가 됐을 때 발생하는 이벤트
    @EventListener(value = ApplicationReadyEvent.class)
    public void initData() {
        log.info("실습용 데이터 초기화");
        memberRepository.save(createMember("member1@naver.com", "password", MemberRole.USER));
        memberRepository.save(createMember("member2@naver.com", "password", MemberRole.USER));
        memberRepository.save(createMember("member3@naver.com", "password", MemberRole.USER));
        memberRepository.save(createMember("member4@naver.com", "password", MemberRole.USER));
        memberRepository.save(createMember("member5@naver.com", "password", MemberRole.USER));
    }
}
