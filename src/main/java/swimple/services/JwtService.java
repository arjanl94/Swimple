package swimple.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import swimple.annotation.PropertiesFromFile;
import swimple.models.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.Properties;

@Stateless
public class JwtService {

    @Inject
    @PropertiesFromFile
    Properties props;

    private Algorithm algorithm;
    private String ISSUER;

    private long EXPIRATION_DATE;

    @PostConstruct
    public void initialize() {
        algorithm = Algorithm.HMAC256(props.getProperty("secret_key"));
        ISSUER = "swimple_api";
        EXPIRATION_DATE = 259200000;
    }


    public String issueToken(User user) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .withArrayClaim("roles", user.getRoles())
                .sign(algorithm);
    }

    public String validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
