package com.learn.daos.price.impl;

public class DbDao {
    public Double getPriceByProductId(String productId) {
        System.out.println(">>> DbDao:getPriceByProductId");

        return switch (productId) {
            case "productFromRedis" -> 100d;
            case "productFromDb" -> 200d;
            case "productFromApi" -> 300d;
            default -> null;
        };
    }
}
