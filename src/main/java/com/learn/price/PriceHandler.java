package com.learn.price;

public interface PriceHandler {
    Double fetchPrice(String productId);
    void setNextHandler(PriceHandler nextHandler);
}