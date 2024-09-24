package project_1st_team03.dashboard.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        if (request.getAttribute("JwtFilterException") instanceof Exception exception) {
            log.error("[Exception in JwtFilter] ", exception);
        }

        // 클라이언트에는 인증이 실패했다는 정보만 노출
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401에러
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter()
                .write(objectMapper.writeValueAsString("인증이 실패하였습니다.")); // 스프링 컨테이너를  거치는 것이 아니기때문에 한글 처리를 위해 필요한 부분

    }

}

