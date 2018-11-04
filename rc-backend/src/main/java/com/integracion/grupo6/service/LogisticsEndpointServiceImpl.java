package com.integracion.grupo6.service;

import com.integracion.grupo6.dto.LogisticsEndpointDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Service
public class LogisticsEndpointServiceImpl implements LogisticsEndpointService {

    private static final String BASE_ENDPOINT = "http://localhost:8080";
    private static final String URI_ENDPOINT = "/prueba-endpoint";

    private WebClient webClient;

    /*
    public LogisticsEndpointServiceImpl() {
        webClient = WebClient
                .builder()
                .baseUrl(ENDPOINT)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                // .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();
    }*/

    @Override
    public void sendClaimToLogistics(String orderId) {
        LogisticsEndpointDTO dto = new LogisticsEndpointDTO(orderId);
        WebClient.RequestHeadersSpec<?> requestSpec2 = WebClient
                .create(BASE_ENDPOINT)
                .post()
                .uri(URI.create(URI_ENDPOINT))
                .body(BodyInserters.fromObject(dto));
    }
}
