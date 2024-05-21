package com.learn;

import com.learn.price.PriceHandler;
import com.learn.price.impl.ApiPriceHandler;
import com.learn.price.impl.DatabasePriceHandler;
import com.learn.price.impl.DynamicSequenceHandler;
import com.learn.price.impl.RedisPriceHandler;
import com.learn.services.price.PriceService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //            case "productFromRedis" -> 100d;
        //            case "productFromDb" -> 200d;
        //            case "productFromApi" -> 300d;
        //

        Double price = null;
        PriceHandler rpriceHandler = new RedisPriceHandler();
        PriceHandler dpriceHandler = new DatabasePriceHandler();
        PriceHandler apriceHandler = new ApiPriceHandler();
        DynamicSequenceHandler dynamicSequenceHandler = new DynamicSequenceHandler();

        dynamicSequenceHandler.addHandler(rpriceHandler);
        dynamicSequenceHandler.addHandler(dpriceHandler);
        dynamicSequenceHandler.addHandler(apriceHandler);

//        rpriceHandler.setNextHandler(dpriceHandler);
//        dpriceHandler.setNextHandler(apriceHandler);

//        PriceService priceService = new PriceService(rpriceHandler);
        PriceService priceService = new PriceService(dynamicSequenceHandler);

        System.out.println("************Price From REDIS*************");
        price = priceService.getPrice("productFromRedis");
        System.out.println("price : "+ price);

        System.out.println("************Price From DB*************");
        price = priceService.getPrice("productFromDb");
        System.out.println("price : "+ price);

        System.out.println("************Price From API*************");
        price = priceService.getPrice("productFromApi");
        System.out.println("price : "+ price);

        System.out.println("************Price Not Found*************");

        price = priceService.getPrice("X");
        System.out.println("price : "+ price);
        System.out.println("***********END*************");

    }
}