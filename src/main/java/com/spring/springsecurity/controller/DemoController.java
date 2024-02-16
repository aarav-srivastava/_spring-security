package com.spring.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping
    public String defaultPage(){
        return "default page";
    }
    @GetMapping("/testing")
    public String testing(){
        return "testing application";
    }
}
