package com.integracion.grupo6.service;

import com.integracion.grupo6.dto.StoreIntegrationDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StoreEndpointServiceImpl implements StoreEndpointService {

    @Value("${endpoint.store.uri}")
    private String uri;

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void sendClaimToStore(StoreIntegrationDTO dto) {

        RestTemplate restTemplate = new RestTemplate();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(2000);
        requestFactory.setReadTimeout(2000);
        restTemplate.setRequestFactory(requestFactory);

        String response = restTemplate.postForObject(uri, dto, String.class);
        logger.warn(String.format("HTTP_POST: %s; RESPONSE: %s", uri, response));
    }
}
