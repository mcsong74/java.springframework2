package com.cybertek.util;

import com.cybertek.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtil {
    @Value("${security.jwt.secret-key}") //in application.yml
    private String secret = "cybertek";

    //create payload and encoded Token String
    public String generateToken(User user, String username){
        // generating payload for Token
        //in spring, payload called claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        return createToken(claims, username);

    }
    //create Token return encoded Token string
    private String createToken(Map<String, Object> claims, String username){

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000*60*60*60*10))) //10 hr
                .signWith(SignatureAlgorithm.HS256, secret) //signature
                .compact();
    }

    //decode a token return as Claims object
    private Claims extractAllClaims(String token){

        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
    //get a claim value from a token by claim property in param
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    //get user name from token
    private String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    //get expiration from token
    public  Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

}