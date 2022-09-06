package com.realestate.Security.jwt;

import com.realestate.Security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${cosmosRestate.app.jwtSecret}")
    private String jwtSecret;

    @Value("${cosmosRestate.app.jwtExpirationMs}")
    private Long jwtExpirationMs;

    //
    public String generateToken(Authentication authentication){

        UserDetailsImpl userDetails= (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().setSubject(""+userDetails.getId()) // token'ın içindeki Id
                .setIssuedAt(new Date()) //token oluşturma zamanı
                .setExpiration(new Date(new Date().getTime()+jwtExpirationMs))// token exp süresi
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // token imza algoritması + secret
                .compact(); // tümünü toparla

    }

    //oluşturduğumuz token'dan, ilgili kullanıcının id'sini çekiyoruz
    public Long getIdFromToken(String token){
        String strId = Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();

        return Long.parseLong(strId);
    }

    //validate token
    public boolean validateToken(String token){

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;

        } catch (ExpiredJwtException e) {
            logger.error("JWT Token is expired {}",e.getMessage() );
        } catch (UnsupportedJwtException e) {
            logger.error("JWT Token is Unsupported {}",e.getMessage() );
        } catch (MalformedJwtException e) {
            logger.error("JWT Token is Malformed {}",e.getMessage() );
        } catch (SignatureException e) {
            logger.error("Invalid JWT Signature {}",e.getMessage() );
        } catch (IllegalArgumentException e) {
            logger.error("JWT Token illegal args {}",e.getMessage() );
        }

        return false;

    }




}
