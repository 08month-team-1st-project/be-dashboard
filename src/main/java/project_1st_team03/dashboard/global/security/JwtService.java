package project_1st_team03.dashboard.global.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 토큰 재발급, 리프레시 토큰 등의 로직은 시간상 구현 X
 */
@Service
public class JwtService {
    
    @Value("${jwt.secretKey}") // base64 인코딩한 키값으로 초기화
    private static final String SECRET_KEY = "SECRET_KEY";
    private static final long ACCESS_TOKEN_VALID_TIME = 1000L * 60 * 30; // 30분



    // 추가 클레임 없이 토큰 생성
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims,
                                UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims) // 추가 클레임 설정 ex) 역할, 권한 등
                .setSubject(userDetails.getUsername())// 토큰의 주체 설정 (보통 사용자 이름  or 이메일)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 생성 시기
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_TIME)) // 토큰 만료 시간
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // 알고리즘과 Key 객체를 넣어서 서명
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    /** JWT 토큰에서 특정 클레임(claim)을 추출 */
    public<T> T extractClaim(String token, Function<Claims, T> claimsResolver) { // 함수형 인터페이스
        final Claims claims = extractAllClaims(token); // 토큰에서 모든 클레임을 추출
        return claimsResolver.apply(claims); // 추출한 클레임을 함수에 전달해 원하는 클레임을 반환
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey()) // 디코딩 위해선 서명 키 필요
                .build()
                .parseClaimsJws(token)
                .getBody(); // body에서 가지고있는 모든 클레임을 가져올 수 있음
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes); // HMAC-SHA 알고리즘에 맞는 Key 객체 생성
    }

}
