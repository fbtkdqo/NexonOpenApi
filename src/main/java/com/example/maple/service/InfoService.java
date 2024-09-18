package com.example.maple.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class InfoService {

    @Value("${maple.key}")
    private String key;

    public Map<String, Object> infoService(String ocid) {
        Map<String, Object> CharacterInfo = null;

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("x-nxopen-api-key", key);

            String url = "https://open.api.nexon.com/maplestory/v1/character/basic?ocid=" + ocid;

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<>() {}
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Success: " + response.getBody());
            } else {
                System.out.println("Error: " + response.getStatusCode());
            }
            CharacterInfo = response.getBody();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return CharacterInfo;
    }
}
