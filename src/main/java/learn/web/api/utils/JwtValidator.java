package learn.web.api.utils;

import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.List;

@Component
public class JwtValidator {

    private static final List<String> allowedIssusers = Collections.singletonList("https://distinct-dory-88.clerk.accounts.dev");

    private String getKeycloakCertificateUrl(DecodedJWT token) {
        return token.getIssuer() + "/.well-known/jwks.json";
    }

    private RSAPublicKey loadPublicKey(DecodedJWT token) throws JwkException, MalformedURLException {

        final String url = getKeycloakCertificateUrl(token);
        JwkProvider provider = new UrlJwkProvider(new URL(url));

        return (RSAPublicKey) provider.get(token.getKeyId()).getPublicKey();
    }

    public String decodeToJson(final String base64) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(base64));
    }

    public String getUserIdfromToken(final String token) {
        DecodedJWT validate = validate(token);
        JSONObject jsonObj = new JSONObject(decodeToJson(validate.getPayload()));
        return jsonObj.getString("userId");
    }

    /**
     * Validate a JWT token
     *
     * @param token
     * @return decoded token
     */
    private DecodedJWT validate(String token) {
        try {
            final DecodedJWT jwt = JWT.decode(token);

            if (!allowedIssusers.contains(jwt.getIssuer())) {
                throw new InvalidParameterException(String.format("Unknown Issuer %s", jwt.getIssuer()));
            }

            RSAPublicKey publicKey = loadPublicKey(jwt);

            Algorithm algorithm = Algorithm.RSA256(publicKey, null);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(jwt.getIssuer())
                    .build();

            return jwt;

        } catch (Exception e) {

            throw new InvalidParameterException("JWT validation failed: " + e.getMessage());
        }
    }
}
