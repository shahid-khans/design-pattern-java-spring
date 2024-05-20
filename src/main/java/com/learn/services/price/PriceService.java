package com.learn.services.price;

import com.learn.price.PriceHandler;


public class PriceService {
    private PriceHandler priceHandler;

    public PriceService(PriceHandler priceHandler) {
        this.priceHandler = priceHandler;
    }

    public Double getPrice(String productId) {
        return priceHandler.fetchPrice(productId);
    }
}
