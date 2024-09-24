package project_1st_team03.dashboard.global.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 일정의 문제로 구현 간소화
 * - 토큰재발급, 리프레시 토큰 등의 로직 X
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        log.debug("jwt 인증필터 진입");

        // 요청 시에 Authorization 이라는 헤더로 jwt 인증 토큰을 전달한 상태
        // 해당 헤더에 담아온 토큰을 꺼낸다
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        try {
            // 토큰이 필요하지 않은 api일 경우 로직 처리없이 다음 필터로 이동
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            jwtToken = authHeader.substring(7); // Bearer 접두사 제거하여 토큰추출
            userEmail = jwtService.extractUsername(jwtToken);


            //  아직 인증 상태가 아닐때 (null 이면 아직 인증 X)
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail); // db 로부터 사용자정보 로드

                if (jwtService.isTokenValid(jwtToken, userDetails)) {

                    // 인증 객체(Authentication) 얻기
                    // 참고: UsernamePasswordAuthenticationToken (구현체) -> Authentication
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,   // 현재 구현에서는 자격증명이 없기에 null로 넘김
                            userDetails.getAuthorities() // 권한 넘김
                    );

                    // 참고: WebAuthenticationDetailsSource (구현체) -> AuthenticationDetailsSource
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request) // WebAuthenticationDetails 반환
                    );

                    /* 관리대상) SecurityContextHolder -> SecurityContext -> Authentication -> 사용자 인증 정보
                     *
                     * SecurityContextHolder 내부에 있는 값을 통해 인증여부를 알 수 있음
                     * SecurityContextHolder.getContext().getAuthentication() == null -> 인증상태 X */
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            request.setAttribute("JwtFilterException", e);
        }
        filterChain.doFilter(request, response);
    }
}
