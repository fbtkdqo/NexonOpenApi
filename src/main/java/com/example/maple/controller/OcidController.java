package com.example.maple.controller;

import com.example.maple.service.OcidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class OcidController {

    @Autowired
    private OcidService ocidService;
    @GetMapping("/ocid")
    public String ocid(@RequestParam(name= "name") String name) {
        System.out.println(">>>>>>>>>>>1"+name);
        String encodeName = URLEncoder.encode(name,StandardCharsets.UTF_8);

        String ocid = ocidService.ocidService(encodeName);

        System.out.println(ocid);

        return ocid;
    }
}
