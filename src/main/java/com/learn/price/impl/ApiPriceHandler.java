package com.learn.price.impl;

import com.learn.daos.price.PriceRepository;
import com.learn.daos.price.impl.APIDao;
import com.learn.price.PriceHandler;


public class ApiPriceHandler implements PriceHandler {

    private PriceRepository priceRepository ;
    private boolean handle = PriceHandler.super.canHandle();

    private PriceHandler nextHandler;

    @Override
    public Double fetchPrice(String productId) {

        if(!canHandle()) {
            System.out.println(String.format("Skipping Handler %s ,canHandle is  %s", getClass().getName() , canHandle()));
            return nextHandler != null ? nextHandler.fetchPrice(productId) : null;
        }
        System.out.println(">>>3. ApiPriceHandler");
        this.priceRepository = new APIDao();

        String apiUrl = "http://externalapi.com/prices/" + productId;

        try {
            Double price = priceRepository.findPriceByProductId(productId);
            if (price != null) {
                return price;
            } else if (nextHandler != null) {
                return nextHandler.fetchPrice(productId);
            }

        } catch (Exception e) {
            // Handle exception
            return null;
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
