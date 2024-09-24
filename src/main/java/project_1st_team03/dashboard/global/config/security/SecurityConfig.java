package project_1st_team03.dashboard.global.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import project_1st_team03.dashboard.global.security.JwtAuthenticationEntryPoint;
import project_1st_team03.dashboard.global.security.JwtAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // rest api 는 csrf 보안이 필요 없으므로 비활성화
                .sessionManagement((sessionManagement) ->
                        // jwt 인증방식으로 세션은 필요 없으므로 비활성화
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/api/signup").permitAll()
                                .requestMatchers("/api/login").permitAll()
                                .requestMatchers("/posts/search/**").permitAll()
                                .requestMatchers("/posts/**").permitAll()
                                .requestMatchers("/comments/**").permitAll()
                                .anyRequest().authenticated() // 그 외의 요청은 인증
                )
                .exceptionHandling(
                        (exceptionConfig) ->
                                exceptionConfig.authenticationEntryPoint(jwtAuthenticationEntryPoint)

                ).addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
