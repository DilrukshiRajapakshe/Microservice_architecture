package com.adams.zullrouting.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTWebService {
    @Value( "${adams.payment_management_system.SECRET_KEY}" )
    private String SECRET_KEY;

    public String extract_Username(String token) {
        return extract_Claim(token, Claims::getSubject);
    }

    public Date extract_Expiration(String token) {
        return extract_Claim(token, Claims::getExpiration);
    }

    public <T> T extract_Claim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extract_All_Claims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extract_All_Claims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean is_Token_Expired(String token) {
        return extract_Expiration(token).before(new Date());
    }

    public String generate_Token(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return create_Token(claims, userDetails.getUsername());
    }

    private String create_Token(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validate_Token(String token, UserDetails userDetails) {
        final String username = extract_Username(token);
        return (username.equals(userDetails.getUsername()) && !is_Token_Expired(token));
    }

}
