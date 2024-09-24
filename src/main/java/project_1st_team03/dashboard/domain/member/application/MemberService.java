package project_1st_team03.dashboard.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project_1st_team03.dashboard.domain.member.dao.MemberRepository;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.member.dto.SignupRequest;
import project_1st_team03.dashboard.domain.member.exception.MemberException;
import project_1st_team03.dashboard.global.security.MemberRole;

import static project_1st_team03.dashboard.global.exception.ErrorCode.ALREADY_REGISTERED_EMAIL;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void createMember(SignupRequest request) {

        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new MemberException(ALREADY_REGISTERED_EMAIL);
        }
        Member member = Member.createMember(request.getEmail(),
                bCryptPasswordEncoder.encode(request.getPassword()),
                MemberRole.USER);

        memberRepository.save(member);
    }
}
