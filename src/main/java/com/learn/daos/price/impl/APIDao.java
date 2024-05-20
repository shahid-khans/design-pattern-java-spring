package com.learn.daos.price.impl;

import com.learn.daos.price.PriceRepository;

public class APIDao implements PriceRepository {
    public Double getPriceByProductId(String productId) {
        System.out.println(">>> APIDao:getPriceByProductId " + productId);
        if (productId.equals("productFromApi")) return 300d;
        else return null;
    }

    @Override
    public Double findPriceByProductId(String productId) {
        return this.getPriceByProductId(productId);
    }
}
