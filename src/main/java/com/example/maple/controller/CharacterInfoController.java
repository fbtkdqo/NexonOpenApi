package com.example.maple.controller;

import com.example.maple.service.OcidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CharacterInfoController {

    @Autowired
    private OcidService ocidService;
    @GetMapping("/ocid")
    public Map<String, Object> ocid(@RequestParam(name= "name") String name) {
        System.out.println(">>>>>>>>>>>1"+name);

        Map<String, Object> ocid = ocidService.ocidService(name);

        System.out.println(ocid);

        return ocid;
    }
}
