package com.example.backenddevtest.myapp.controller;

import com.example.backenddevtest.myapp.domain.Item;
import com.example.backenddevtest.myapp.service.myAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("product")
public class myAppController {
    private static final String NOT_FOUND_ERROR_MSG = "Product Not Found";
    private static final String UNEXPECTED_ERROR = "Unexpected Error";

    private myAppService service;

    @Autowired
    public myAppController(myAppService service) {
        this.service = service;
    }

    @GetMapping("{productId}/similar")
    ResponseEntity<List<Item>> getSimilarProducts(@PathVariable("productId") String productId) {

        try {
            return new ResponseEntity<>(service.getSimilarProducts(productId), HttpStatus.OK);

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return new ResponseEntity(NOT_FOUND_ERROR_MSG, e.getStatusCode());
            }
            return new ResponseEntity(e.getStatusText(), e.getStatusCode());
        }
    }
}
