package project_1st_team03.dashboard.domain.member.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project_1st_team03.dashboard.domain.member.application.MemberService;
import project_1st_team03.dashboard.domain.member.dto.LoginRequest;
import project_1st_team03.dashboard.domain.member.dto.LoginResponse;
import project_1st_team03.dashboard.domain.member.dto.SignupRequest;
import project_1st_team03.dashboard.global.security.MemberDetails;


/** 로그아웃 기능
 * 일정상 토큰 재발급, 리프레시 토큰을 구현하지 않고 제출하기로 계획한 터라
 * 백엔드의 로그아웃 api 에서 처리해줄 로직이 그닥 없는 듯하다.
 * (현재 제공된 프론트 코드에서는 쿠키가 아닌 로컬스토리지를 이용한 코드인 상태)
 *
 * 프론트 코드 수정할 때 상황 봐서 구현할 듯 하다.
 */
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid SignupRequest request) {

        memberService.createMember(request);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = memberService.login(request);
        return ResponseEntity.ok(response);
    }

    /** TODO 로그아웃: 프론트 코드 수정 & 일정에 따라서 수정 계획
     * 인증이 필요한 요청에 잘 적용되는지 확인하는 용으로만 구현해둔 상태
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal MemberDetails memberDetails) {
        memberService.logout(memberDetails);
        return ResponseEntity.ok("로그아웃되었습니다.");
    }

}
