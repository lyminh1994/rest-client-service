package com.minhlq.restclientservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Component
@RequiredArgsConstructor
public class HttpHandler {

    private final RestTemplate restTemplate;

    private final ObjectMapper mapper;

    public <T> T toObject(String content, Class<T> valueType) throws IOException {
        return mapper.readValue(content, valueType);
    }

    public <T> List<T> toObjects(String content, Class<T> valueType) throws IOException {
        return mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, valueType));
    }

    private <T> Map<String, T> toMap(String content, Class<T> valueType) throws IOException {
        return mapper.readValue(content, mapper.getTypeFactory().constructMapType(Map.class, String.class, valueType));
    }

    public String execute(String url, HttpMethod method, Object body) {
        HttpEntity<Object> requestEntity = HttpEntityUtils.entityWithAuth(StringUtils.EMPTY, body);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, method, requestEntity, String.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return responseEntity.getBody();
        }

        return StringUtils.EMPTY;
    }

    public String execute(String url, Object body) {
        HttpEntity<Object> requestEntity = HttpEntityUtils.entityWithAuth(StringUtils.EMPTY, body,
            MediaType.MULTIPART_FORM_DATA);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
            String.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return responseEntity.getBody();
        }

        return StringUtils.EMPTY;
    }

    public String execute(String url) {
        HttpEntity<Object> requestEntity = HttpEntityUtils.entityWithAuth(null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return responseEntity.getBody();
        }

        return StringUtils.EMPTY;
    }

    public <T> T postGetSingleResult(String url, Object body, Class<T> valueType) {
        try {
            return toObject(execute(url, HttpMethod.POST, body), valueType);
        } catch (IOException ex) {
            throw new ResourceAccessException("Parses result to Object fails", ex);
        }
    }

    public <T> List<T> postGetListResult(String url, Object body, Class<T> valueType) {
        try {
            return toObjects(execute(url, HttpMethod.POST, body), valueType);
        } catch (IOException ex) {
            throw new ResourceAccessException("Parses result to List fails", ex);
        }
    }

    public <T> Map<String, T> postGetMapResult(String url, Object body, Class<T> valueType) {
        try {
            return toMap(execute(url, HttpMethod.POST, body), valueType);
        } catch (IOException ex) {
            throw new ResourceAccessException("Parses result to Map fails", ex);
        }
    }
}
