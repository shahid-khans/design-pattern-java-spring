package com.learn.price.impl;

import com.learn.daos.price.PriceRepository;
import com.learn.daos.price.impl.RedisDao;
import com.learn.price.PriceHandler;


public class RedisPriceHandler implements PriceHandler {
    private PriceRepository priceRepository;

    private PriceHandler nextHandler;

    @Override
    public Double fetchPrice(String productId) {
        this.priceRepository = new RedisDao();
//        String priceStr = redisTemplate.opsForValue().get(productId);
        Double price = null;
        if(!canHandle()) {
            System.out.println(String.format("Skipping Handler %s ,canHandle is  %s", getClass().getName() , canHandle()));
            return nextHandler != null ? nextHandler.fetchPrice(productId) : null;
        }
        System.out.println(">>>1. RedisPriceHandler");
        price = priceRepository.findPriceByProductId(productId);
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

    @Override
    public boolean canHandle() {
        return false;
    }
}
