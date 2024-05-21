package com.learn.price.impl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.learn.daos.price.PriceRepository;
import com.learn.price.PriceHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class RedisPriceHandlerTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceHandler nextHandler;

    @InjectMocks
    private RedisPriceHandler redisHandler;


    @Before
    public void setUp() {
//        redisHandler = new RedisPriceHandler();
//        redisHandler.setNextHandler(null);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchPriceFromRedis() {
        when(priceRepository.findPriceByProductId("productFromRedis")).thenReturn(100.0);

        Double price = redisHandler.fetchPrice("productFromRedis");
        assertNotNull(price);
        assertEquals(100.0, price, 0.0);
    }

    @Test
    public void testFetchPriceFromRedisMarkedFalse() {
        when(priceRepository.findPriceByProductId("productFromRedis")).thenReturn(100.0);
        redisHandler.markHandle(false);
        Double price = redisHandler.fetchPrice("productFromRedis");
        assertNotNull(price);
        assertEquals(0.0, price, 0.0);
    }

    @Test
    public void testFetchPriceNotFoundInRedis() {
        when(priceRepository.findPriceByProductId("productFromDb")).thenReturn(null);
        when(nextHandler.fetchPrice(anyString())).thenReturn(null);
        Double price = redisHandler.fetchPrice("productFromDb");
        assertNull(price);
    }

    @Test
    public void testFetchPriceFoundInAPI() {
        when(priceRepository.findPriceByProductId("productFromApi")).thenReturn(null);
        when(nextHandler.fetchPrice(anyString())).thenReturn(301d);
        Double price = redisHandler.fetchPrice("productFromApi");
        assertNotNull(price);
    }

    @Test
    public void testFetchPriceNotFoundInAPI() {
        redisHandler.setNextHandler(null);
        when(priceRepository.findPriceByProductId("productFromApi")).thenReturn(null);
        when(nextHandler.fetchPrice(anyString())).thenReturn(301d);
        Double price = redisHandler.fetchPrice("productFromApi");
        assertNull(price);
    }
}
