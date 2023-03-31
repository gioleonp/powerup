package com.pragma.plazoleta.infrastructure.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pragma.plazoleta.domain.model.RolModel;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import com.pragma.plazoleta.infrastructure.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenUtils {

    private String SECRET_KEY = System.getenv("SECRET_KEY");

    private Long TOKEN_TIME_VALIDITY = 86400L;

    private final JWTVerifier verifier =
            JWT.require(Algorithm.HMAC256(SECRET_KEY.getBytes())).build();
    private final IUserServiceCommunicationPort userServiceCommunicationPort;

    public String createToken(UserDetailsImpl userDetails) {

        List<GrantedAuthority> authorities = (List<GrantedAuthority>) userDetails.getAuthorities();

        return JWT.create()
                .withIssuer("plazoleta service")
                .withSubject(userDetails.getUsername())
                .withClaim("id", userDetails.getUserResponseDto().getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_TIME_VALIDITY * 1000))
                .sign(Algorithm.HMAC256(SECRET_KEY.getBytes()));
    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        try {
            DecodedJWT claim = verifier.verify(token);
            String id = claim.getClaim("id").toString();

            RolModel role = userServiceCommunicationPort.findUserById(Long.parseLong(id)).getRol();

            List<? extends GrantedAuthority> grantedAuthorities =
                    Arrays.asList(new SimpleGrantedAuthority(role.getNombre()));

            String username = claim.getSubject();

            return new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
        } catch (JWTVerificationException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
