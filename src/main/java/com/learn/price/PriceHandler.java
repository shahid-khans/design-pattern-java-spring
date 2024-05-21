package com.learn.price;

public interface PriceHandler {
    Double fetchPrice(String productId);
    void setNextHandler(PriceHandler nextHandler);
    default int getPriority() {
        return 1;
    }
    default boolean canHandle() {
        return true;
    }
}