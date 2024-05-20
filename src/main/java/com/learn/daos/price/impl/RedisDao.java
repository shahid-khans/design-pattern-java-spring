package com.learn.daos.price.impl;


import com.learn.daos.price.PriceRepository;

public class RedisDao implements PriceRepository  {
    public Double getPriceByProductId(String productId) {
        System.out.println(">>> RedisDao:getPriceByProductId " + productId);
        if (productId.equals("productFromRedis")) return 100d;
        else return null;

    }

    @Override
    public Double findPriceByProductId(String productId) {
        return getPriceByProductId(productId) ;
    }
}
