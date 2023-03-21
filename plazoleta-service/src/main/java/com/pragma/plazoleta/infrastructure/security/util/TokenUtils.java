package com.pragma.plazoleta.infrastructure.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pragma.plazoleta.infrastructure.security.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TokenUtils {

    private final String SECRET_KEY = "1231rfdsfcseafas";
    private final Long TOKEN_TIME_VALIDITY = 1_313_131L;
    private final JWTVerifier verifier =
            JWT.require(Algorithm.HMAC256(SECRET_KEY.getBytes())).build();


    public String createToken(UserDetailsImpl userDetails){

        List<GrantedAuthority> authorities = (List<GrantedAuthority>) userDetails.getAuthorities();

        return JWT.create()
                .withIssuer("plazoleta service")
                .withSubject(userDetails.getUsername())
                .withClaim("authorities", authorities.toString())
                .withClaim("id", userDetails.getUserResponseDto().getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_TIME_VALIDITY * 1000))
                .sign(Algorithm.HMAC256(SECRET_KEY.getBytes()));
    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
        try {
            DecodedJWT claim = verifier.verify(token);
            List<GrantedAuthority> authorities =
                    claim.getClaim("authorities").asList(GrantedAuthority.class);

            String username = claim.getSubject();

            return new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities
            );
        } catch (JWTVerificationException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
