package com.example.backenddevtest.myapp.service;

import com.example.backenddevtest.myapp.domain.Item;

import java.util.List;

public interface myAppService {

    List<Item> getSimilarProducts(String productId);
}
