package com.nissum.test.userservice.security;

import com.nissum.test.userservice.model.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * JwtProvider Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Component
public class JwtProvider {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration}")
    private long expiration;

    @Value("${security.jwt.authorities-key}")
    private String authoritiesKey;

    /**
     * Generate Token
     *
     * @param username
     * @param roles
     * @return String
     */
    public String generateToken(String username, List<Role> roles){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(authoritiesKey, roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(generateSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Is Token Valid
     *
     * @param requestToken
     * @param userToken
     * @param userDetails
     * @return boolean
     */
    public boolean isTokenValid(String requestToken, String userToken, UserDetails userDetails) {
        final String username = extractUsername(requestToken);
        return (username.equals(userDetails.getUsername())) && (requestToken.equals(userToken)) && !isTokenExpired(requestToken);
    }

    /**
     * Extract Username
     *
     * @param token
     * @return String
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Is Token Expired
     *
     * @param token
     * @return bookean
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extract Expiration
     *
     * @param token
     * @return Date
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extract Claim
     *
     * @param token
     * @param claimsResolver
     * @param <T>
     * @return T
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract All Claims
     *
     * @param token
     * @return Claims
     */
    private Claims extractAllClaims(String token) {
        return getJwtParserBuilder()
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Get Jwt Parser Builder
     *
     * @return JwtParserBuilder
     */
    private JwtParserBuilder getJwtParserBuilder() {
        return Jwts.parserBuilder().setSigningKey(generateSignInKey());
    }

    /**
     * Generate Sign In Key
     *
     * @return Key
     */
    private Key generateSignInKey() {
        String encodedSecreteKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        return Keys.hmacShaKeyFor(encodedSecreteKey.getBytes(StandardCharsets.UTF_8));
    }

}
