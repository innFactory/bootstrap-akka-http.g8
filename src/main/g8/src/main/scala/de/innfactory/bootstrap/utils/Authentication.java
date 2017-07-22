package de.innfactory.bootstrap.utils;

import java.util.Map;

public interface Authentication {
    Map<String, Object> validateToken(String token);
}
