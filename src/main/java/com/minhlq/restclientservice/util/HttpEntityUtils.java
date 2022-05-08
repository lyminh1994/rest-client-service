package com.minhlq.restclientservice.util;

import java.util.Map;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@UtilityClass
public class HttpEntityUtils {

    public HttpEntity<Object> entityWithAuth(String token, Object body) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        return new HttpEntity<>(body, headers);
    }


    public HttpEntity<Object> entityWithAuth(String token, Object body, MediaType contentType) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(contentType);

        return new HttpEntity<>(body, headers);
    }

    public HttpEntity<Object> entityWithAuth(String token, Object body, Map<String, String> contentTypes) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        contentTypes.keySet().forEach(k -> headers.set(k, contentTypes.get(k)));

        return new HttpEntity<>(body, headers);
    }

    public HttpEntity<Object> entityWithAuth(String token) {
        return entityWithAuth(token, null);
    }
}
