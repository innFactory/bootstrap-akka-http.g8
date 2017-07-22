package $package$.utils;

import akka.event.LoggingAdapter;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Map;

public class AWSCognitoValidation implements Authentication {
    // \$COVERAGE-OFF\$Disabling highlighting until it is rewritten in scala
    private JWKSource keySource;
    private String remoteJWKUrl = "";
    private LoggingAdapter log;

    public AWSCognitoValidation(String remoteJWKUrl, LoggingAdapter log){
        this.remoteJWKUrl = remoteJWKUrl;
        this.log = log;
    }

    @Override
    public Map<String, Object> validateToken(String token) {
        try {
            keySource = new RemoteJWKSet(new URL(remoteJWKUrl));
        } catch (MalformedURLException e) {
            log.error(e, e.getMessage());
            return null;
        }
        ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
        JWSAlgorithm expectedJWSAlg = JWSAlgorithm.RS256;
        JWSKeySelector keySelector = new JWSVerificationKeySelector(expectedJWSAlg, keySource);
        jwtProcessor.setJWSKeySelector(keySelector);

        try {
            return jwtProcessor.process(token, null).getClaims();
        } catch (ParseException e) {
            log.error(e, e.getMessage());
            return null;
        } catch (BadJOSEException e) {
            log.error(e, e.getMessage());
            return null;
        } catch (JOSEException e) {
            log.error(e, e.getMessage());
            return null;
        }
    }
    // \$COVERAGE-ON\$
}