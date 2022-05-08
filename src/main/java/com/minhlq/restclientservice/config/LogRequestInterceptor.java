package com.minhlq.restclientservice.config;

import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

@Log4j2
public class LogRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
        throws IOException {
        ClientHttpResponse response = execution.execute(request, body);

        // log the http request
        log.info("URI: {}", request.getURI());
        log.info("HTTP Method: {}", request.getMethodValue());
        log.info("HTTP Headers: {}", request.getHeaders());

        // log the http response
        log.info("Headers: {}", response.getHeaders());
        log.info("Status: {}", response.getStatusCode() + "/" + response.getStatusText());
        log.info("Body: {}", response.getBody());

        return response;
    }
}
