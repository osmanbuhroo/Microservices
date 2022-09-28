package com.microservice.Cloudgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FallBackMethodController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod(){
        return "User Service is talking longer than Expected."+
                "Please try again latter";
    }
    @GetMapping("departmentServiceFallBack")
    public  String departmentServiceFallBackMethod(){
        return "Department Service is talking longer than Expected."+
                "Please try again latter";
    }

}
