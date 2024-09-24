package project_1st_team03.dashboard.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project_1st_team03.dashboard.domain.member.dao.MemberRepository;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.global.security.MemberDetails;

import static project_1st_team03.dashboard.global.exception.ErrorCode.NOT_FOUND_MEMBER;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {

    private final MemberRepository memberRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Member findMember = memberRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_MEMBER.getMessage()));
                return new MemberDetails(findMember);
            }
        };

        // before 람다 & after 람다 비교해려고 넣었음
//        return username -> {
//            Member findMember = memberRepository.findByEmail(username)
//                    .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_MEMBER.getMessage()));
//            return new MemberDetails(findMember);
//        };
    }
}
