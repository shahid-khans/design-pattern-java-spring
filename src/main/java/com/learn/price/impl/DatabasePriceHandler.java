package com.learn.price.impl;

import com.learn.daos.price.PriceRepository;
import com.learn.daos.price.impl.DbDao;
import com.learn.price.PriceHandler;


public class DatabasePriceHandler implements PriceHandler {

    private PriceRepository priceRepository;
    private boolean handle = PriceHandler.super.canHandle();

    private PriceHandler nextHandler;

    @Override
    public Double fetchPrice(String productId) {
        if(!canHandle()) {
            System.out.println(String.format("Skipping Handler %s ,canHandle is  %s", getClass().getName() , canHandle()));
            return nextHandler != null ? nextHandler.fetchPrice(productId) : null;
        }
        System.out.println(">>>2. DatabasePriceHandler");
        this.priceRepository = new DbDao();

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

    @Override
    public boolean canHandle() {
        return this.handle;
    }

    public PriceHandler markHandle(boolean handle) {
        this.handle = handle;
        return this;
    }
}
