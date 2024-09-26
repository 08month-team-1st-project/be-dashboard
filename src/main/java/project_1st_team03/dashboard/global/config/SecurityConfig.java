package project_1st_team03.dashboard.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import project_1st_team03.dashboard.global.security.JwtAuthenticationEntryPoint;
import project_1st_team03.dashboard.global.security.JwtAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

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
                .cors((corsConfig)->corsConfig.configurationSource(corsConfigurationSource()))
                .sessionManagement((sessionManagement) ->
                        // jwt 인증방식으로 세션은 필요 없으므로 비활성화
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/api/signup").permitAll()
                                .requestMatchers("/api/login").permitAll()
                                .requestMatchers("/api/posts/search/**").permitAll()
                                .requestMatchers(GET,"/api/posts/**").permitAll()
                                .requestMatchers(GET,"/api/comments/**").permitAll()
                                .requestMatchers(GET,"/api/replies/**").permitAll()
                                .anyRequest().authenticated() // 그 외의 요청은 인증
                )
                .exceptionHandling(
                        (exceptionConfig) ->
                                exceptionConfig.authenticationEntryPoint(jwtAuthenticationEntryPoint)

                ).addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowCredentials(true); //token 주고 받을 때
        configuration.addExposedHeader("Authorization"); //token이 잘 주고받기가 안될때
        configuration.addAllowedHeader("*");
        configuration.setAllowedMethods(Arrays.asList("GET","PUT","POST","PATCH","DELETE","OPTIONS"));
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
