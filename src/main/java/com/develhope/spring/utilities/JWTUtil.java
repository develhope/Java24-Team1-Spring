package com.develhope.spring.utilities;
import com.develhope.spring.models.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
CLASSE PER LA GESTIONE DEL TOKEN (CREAZIONE, VALIDAZIONE, ESTRAZIONE CLAIMS)

I CLAIMS sono info contenute all'interno del token. E sono:
        -"issuer": chi ha emesso il token
        -"subject": soggetto del token
        -"expiration": durata del token
 */
@Component
public class JWTUtil {
    private final String secret = "vRURNqOAwQf2OAe3cminZOzmFVoIoHij";


    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public String createToken(UserDetailsImpl userDetails) {
        return Jwts.builder()
                .claim("role", userDetails.getAuthorities())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) //DATA INIZIO TOKEN
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) //TOKEN DURA 1 ORA
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
