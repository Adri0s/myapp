package com.example.backenddevtest.myapp.domain;

import java.math.BigDecimal;

public record Item(String id, String name, BigDecimal price, boolean availability) { }
