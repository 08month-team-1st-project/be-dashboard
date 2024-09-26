package project_1st_team03.dashboard.global.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project_1st_team03.dashboard.domain.member.domain.Member;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
public class MemberDetails implements UserDetails {

    private Long id;

    private String email;

    private String password;

    private MemberRole role;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd_HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public MemberDetails(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.role = member.getRole();
        this.createdAt = member.getCreatedAt();
        this.modifiedAt = member.getModifiedAt();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // 현재 하나의 역할만 가질 수 있도록 해놨기때문에 SimpleGrantedAuthority(단순 부여 권한) 반환
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
