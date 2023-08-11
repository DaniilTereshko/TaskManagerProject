package org.taskmanager.audit_server.endpoints.web.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.taskmanager.audit_server.config.property.JWTProperty;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtHandler {
    private final JWTProperty property;

    public JwtHandler(JWTProperty property) {
        this.property = property;
    }
    public String generateAccessToken(String name){
        return Jwts.builder()
                .setSubject(name)
                .setIssuer(this.property.getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7)))
                .signWith(SignatureAlgorithm.HS512, this.property.getSecret())
                .compact();
    }
    public String getEmail(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(this.property.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public Date getExpirationDate(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(this.property.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }
    public String generateSystemAccessToken(String name) {
        return Jwts.builder()
                .setSubject(name)
                .claim("role", "ROLE_SYSTEM")
                .setIssuer(this.property.getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7)))
                .signWith(SignatureAlgorithm.HS512, this.property.getSecret())
                .compact();
    }
    public String getSystemRole(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.property.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }
    public boolean validate(String token){
        try {
            Jwts.parser().setSigningKey(this.property.getSecret()).parseClaimsJws(token);
            return this.getExpirationDate(token).after(new Date());
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            return false;
        }
    }
}
