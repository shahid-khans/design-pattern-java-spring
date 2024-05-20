package com.learn.daos.price.impl;

import com.learn.daos.price.PriceRepository;

public class PriceRepositoryImpl implements PriceRepository {

    DbDao dbDao = new DbDao();
    @Override
    public Double findPriceByProductId(String productId) {
        System.out.println(">>> PriceRepositoryImpl");
        Double price = dbDao.getPriceByProductId(productId);
        return price;
    }
}
