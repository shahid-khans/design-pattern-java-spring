package com.learn.price.impl;

import com.learn.price.PriceHandler;

import java.util.ArrayList;
import java.util.List;

public class DynamicSequenceHandler implements PriceHandler {

    private final List<PriceHandler> handlers = new ArrayList<>();

    public void addHandler(PriceHandler handler) {
        if (!handlers.isEmpty()) {
            handlers.get(handlers.size() - 1).setNextHandler(handler);
        }
        handlers.add(handler);
    }

    @Override
    public Double fetchPrice(String productId) {
        if (!handlers.isEmpty()) {
            return handlers.get(0).fetchPrice(productId);
        }
        return null;
    }

    @Override
    public void setNextHandler(PriceHandler nextHandler)
    {
        throw new UnsupportedOperationException("setNextHandler is not supported in DynamicSequenceHandler");
    }
}