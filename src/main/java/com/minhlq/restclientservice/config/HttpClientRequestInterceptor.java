package com.minhlq.restclientservice.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

@Log4j2
public class HttpClientRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution)
        throws IOException {

        HttpRequest requestWrapper = new HttpRequestWrapper(request);
        List<MediaType> accepts = new ArrayList<>();
        accepts.add(MediaType.APPLICATION_JSON);
        accepts.add(MediaType.MULTIPART_FORM_DATA);
        requestWrapper.getHeaders().setAccept(accepts);

        return execution.execute(requestWrapper, bytes);
    }
}
