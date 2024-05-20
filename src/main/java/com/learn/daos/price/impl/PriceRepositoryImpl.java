package com.learn.daos.price.impl;

import com.learn.daos.price.PriceRepository;

public class PriceRepositoryImpl implements PriceRepository {

    PriceRepository dbDao = new DbDao();
    @Override
    public Double findPriceByProductId(String productId) {
        return dbDao.findPriceByProductId(productId);
    }
}
