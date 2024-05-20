package com.learn.price.impl;

import com.learn.daos.price.PriceRepository;
import com.learn.price.PriceHandler;


public class ApiPriceHandler implements PriceHandler {

    private PriceRepository priceRepository ;
    private PriceHandler nextHandler;

    @Override
    public Double fetchPrice(String productId) {
        System.out.println("ApiPriceHandler");

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
        }
        return null;
    }


    @Override
    public void setNextHandler(PriceHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
