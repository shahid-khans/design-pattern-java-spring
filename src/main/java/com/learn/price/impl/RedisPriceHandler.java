package com.learn.price.impl;

import com.learn.daos.price.PriceRepository;
import com.learn.price.PriceHandler;


public class RedisPriceHandler implements PriceHandler {


    private PriceRepository priceRepository;


    private PriceHandler nextHandler;

    @Override
    public Double fetchPrice(String productId) {
        System.out.println(">>> RedisPriceHandler");

//        String priceStr = redisTemplate.opsForValue().get(productId);
        Double price = priceRepository.findPriceByProductId(productId);
        if (price != null) {
            return price;
        } else if (nextHandler != null) {
            return nextHandler.fetchPrice(productId);
        }
        return null;
    }

    @Override
    public void setNextHandler(PriceHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
