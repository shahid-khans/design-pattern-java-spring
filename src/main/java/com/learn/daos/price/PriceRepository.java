package com.learn.daos.price;

public interface PriceRepository {
    Double findPriceByProductId(String productId);
}
