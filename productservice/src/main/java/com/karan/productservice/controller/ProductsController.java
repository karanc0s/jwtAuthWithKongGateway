package com.karan.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prod")
public class ProductsController {

    @GetMapping("/all")
    public String getAllProducts() {
        return "This is the products Page";
    }
}
