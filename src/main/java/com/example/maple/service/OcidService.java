package com.example.maple.service;

import org.springframework.beans.factory.annotation.Autowired;
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
public class OcidService {

    @Value("${maple.key}")
    private String key;

    @Autowired
    InfoService infoService;

    public Map<String, Object> ocidService(String name) {

        Map<String, Object> info = null;
        try {
            RestTemplate restTemplate = new RestTemplate();

            String url = "https://open.api.nexon.com/maplestory/v1/id?character_name="+name;


            HttpHeaders headers = new HttpHeaders();
            headers.set("x-nxopen-api-key", key);


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

            Map<String, Object> ocid = response.getBody();

            Object ocidInfo = ocid != null ? ocid.get("ocid") : null;

            info = infoService.infoService(ocidInfo != null ? ocidInfo.toString() : null);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }


        return info;
    }
}