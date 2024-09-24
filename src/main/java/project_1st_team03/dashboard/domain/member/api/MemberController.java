package project_1st_team03.dashboard.domain.member.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project_1st_team03.dashboard.domain.member.application.MemberService;
import project_1st_team03.dashboard.domain.member.dto.SignupRequest;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberController {

    private final MemberService memberService;

    // FIXME HttpMessageNotReadableException 계속 발생 중 (일단 그 외엔 정상작동이라서 미뤄둠)
    // 회원저장도 잘되고(db 에 잘 들어감), 요청값 검증도 잘 되고, 구글링해서 나온 원인들 체크해봤으나 해당사항에 없었음
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequest request) {

        memberService.createMember(request);
        return ResponseEntity.ok("회원가입을 축하드립니다.");
    }


}
