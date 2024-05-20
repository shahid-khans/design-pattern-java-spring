package com.learn.daos.price.impl;

import com.learn.daos.price.PriceRepository;

public class DbDao implements PriceRepository {
    private Double getPriceByProductId(String productId) {
        System.out.println(">>> DbDao:getPriceByProductId " + productId);

        if (productId.equals("productFromDb")) return 200d;
        else return null;

    }

    @Override
    public Double findPriceByProductId(String productId) {
        return getPriceByProductId(productId);
    }
}
