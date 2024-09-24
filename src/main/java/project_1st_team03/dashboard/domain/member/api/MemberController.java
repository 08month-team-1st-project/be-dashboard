package project_1st_team03.dashboard.domain.member.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project_1st_team03.dashboard.domain.member.application.MemberService;
import project_1st_team03.dashboard.domain.member.dto.LoginRequest;
import project_1st_team03.dashboard.domain.member.dto.LoginResponse;
import project_1st_team03.dashboard.domain.member.dto.SignupRequest;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequest request) {

        memberService.createMember(request);
        return ResponseEntity.ok("회원가입을 축하드립니다.");
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = memberService.login(request);
        return ResponseEntity.ok(response);
    }



}
