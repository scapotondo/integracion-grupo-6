package com.integracion.grupo6.security;

import com.integracion.grupo6.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;

import static com.integracion.grupo6.config.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.integracion.grupo6.config.Constants.SIGNING_KEY;
import static com.integracion.grupo6.config.Constants.TOKEN_PREFIX;

@Component
public class JwtTokenUtil {

    public String getUsernameFromToken(String token) {
        token = stripTokenFromHeader(token);
        final Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    public Principal getPrincipalFromToken(String token) {
        String user = getUsernameFromToken(token);
        return () -> user;
    }

    public Date getExpirationDateFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.getExpiration();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        return doGenerateToken(user.getUsername());
    }

    private String doGenerateToken(String subject) {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://localhost")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                username.equals(userDetails.getUsername())
                        && !isTokenExpired(token));
    }

    private String stripTokenFromHeader(String input) {
        if (input.startsWith(TOKEN_PREFIX)) {
            input = input.replace(TOKEN_PREFIX, "");
        }
        return input;
    }

}
