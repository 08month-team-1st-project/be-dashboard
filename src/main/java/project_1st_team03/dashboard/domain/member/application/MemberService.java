package project_1st_team03.dashboard.domain.member.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project_1st_team03.dashboard.domain.member.dao.MemberRepository;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.member.dto.LoginRequest;
import project_1st_team03.dashboard.domain.member.dto.LoginResponse;
import project_1st_team03.dashboard.domain.member.dto.SignupRequest;
import project_1st_team03.dashboard.domain.member.exception.MemberException;
import project_1st_team03.dashboard.global.security.JwtService;
import project_1st_team03.dashboard.global.security.MemberDetails;

import java.util.Map;

import static project_1st_team03.dashboard.global.exception.ErrorCode.ALREADY_REGISTERED_EMAIL;
import static project_1st_team03.dashboard.global.exception.ErrorCode.NOT_CORRECT_ACCOUNT_ID_OR_PASSWORD;
import static project_1st_team03.dashboard.global.security.MemberRole.USER;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public void createMember(SignupRequest request) {
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new MemberException(ALREADY_REGISTERED_EMAIL);
        }
        Member member = Member.createMember(request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                USER);

        memberRepository.save(member);
    }

    public LoginResponse login(LoginRequest request) {
        // 보안 상 어떤 값이 틀렸는지 알 수 없게 하기 위해 같은 에러코드를 사용하였음
        Member findMember = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new MemberException(NOT_CORRECT_ACCOUNT_ID_OR_PASSWORD));

        if (!passwordEncoder.matches(request.getPassword(), findMember.getPassword())) {
            throw new MemberException(NOT_CORRECT_ACCOUNT_ID_OR_PASSWORD);
        }

        String accessToken = jwtService.generateToken(Map.of("role", USER),
                new MemberDetails(findMember));

        return new LoginResponse(findMember.getEmail(), accessToken);
    }

    // TODO 로그아웃: 프론트 코드 수정 & 일정에 따라서 수정 계획
    public void logout(UserDetails userDetails) {
        // 임시 확인용
        log.debug(userDetails.getUsername());
        log.debug(userDetails.getAuthorities().toString());
    }
}
