package com.integracion.grupo6.service;

import com.integracion.grupo6.dto.ClaimResolutionDTO;
import com.integracion.grupo6.dto.LogisticsEndpointDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Service
public class LogisticsEndpointServiceImpl implements LogisticsEndpointService {

    private static final String BASE_ENDPOINT = "http://logistica-uade-app.herokuapp.com/logistica/order/%s/complain";

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void sendClaimToLogistics(String orderId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(2000);
        requestFactory.setReadTimeout(2000);
        restTemplate.setRequestFactory(requestFactory);

        String uri = String.format(BASE_ENDPOINT, orderId);
        String response = restTemplate.patchForObject(uri, null, String.class);
        logger.warn(String.format("HTTP_PATCH: %s; RESPONSE: %s", uri, response));
    }
}
