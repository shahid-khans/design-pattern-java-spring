package com.learn.config.price;

import com.learn.price.PriceHandler;
import com.learn.price.impl.ApiPriceHandler;
import com.learn.price.impl.DatabasePriceHandler;
import com.learn.price.impl.DynamicSequenceHandler;
import com.learn.price.impl.RedisPriceHandler;


public class PriceHandlerConfig {
    private String[] handlerSequence = {"database","redis","api"};
    private  DynamicSequenceHandler dynamicSequenceHandler;

    public PriceHandlerConfig(DynamicSequenceHandler dynamicSequenceHandler) {
        this.dynamicSequenceHandler = dynamicSequenceHandler;
    }
    public PriceHandlerConfig() {
        this(new DynamicSequenceHandler());
//        this.dynamicSequenceHandler = dynamicSequenceHandler;
        RedisPriceHandler redisHandler = new RedisPriceHandler();
        DatabasePriceHandler databaseHandler = new DatabasePriceHandler();
        ApiPriceHandler apiHandler = new ApiPriceHandler();

        // Add handlers to the dynamic sequence
        dynamicSequenceHandler.addHandler(redisHandler);
        dynamicSequenceHandler.addHandler(databaseHandler);
        dynamicSequenceHandler.addHandler(apiHandler);
    }

    public PriceHandler getPriceHandler() {
        return dynamicSequenceHandler;
    }
}
