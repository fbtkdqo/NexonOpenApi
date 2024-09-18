package com.example.maple.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OcidService {

    @Value("${maple.key}")
    private String key;

    public String ocidService(String name) {
        String ocid = "";
        System.out.println(">>>>>>>>>2"+name);
        try {
            System.out.println(">>>>>>>>3"+key);
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://open.api.nexon.com/maplestory/v1/id?character_name="+name;


            HttpHeaders headers = new HttpHeaders();
            headers.set("x-nxopen-api-key", key);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Success: " + response.getBody());
            } else {
                System.out.println("Error: " + response.getStatusCode());
            }

            ocid = response.getBody();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return ocid;
    }
}