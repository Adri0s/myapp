package com.example.backenddevtest.myapp.service.impl;

import com.example.backenddevtest.myapp.domain.Item;
import com.example.backenddevtest.myapp.service.myAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class myAppServiceImpl implements myAppService {

    private static final String MOCK_SIMILAR_IDS_URL = "http://localhost:3001/product/{productId}/similarids";
    private static final String MOCK_PRODUCT_URL = "http://localhost:3001/product/{productId}";

    private RestTemplate restTemplate;

    @Autowired
    public myAppServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Item> getSimilarProducts(String productId) {
        // In the contract it specifies that it returns a list of Strings, but it returns a list of Integer
        List<Integer> similarIds = restTemplate.getForObject(MOCK_SIMILAR_IDS_URL, List.class, productId);

        return similarIds
                .stream()
                .map(id -> restTemplate.getForObject(MOCK_PRODUCT_URL, Item.class, id))
                .collect(Collectors.toList());
    }
}
