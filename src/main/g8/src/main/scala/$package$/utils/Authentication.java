package $package$.utils;

import java.util.Map;

public interface Authentication {
    Map<String, Object> validateToken(String token);
}
