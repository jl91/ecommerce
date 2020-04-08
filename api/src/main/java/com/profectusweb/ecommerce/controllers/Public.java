package com.profectusweb.ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public")
public class Public {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String ping(){
        return "OK";
    }
}
