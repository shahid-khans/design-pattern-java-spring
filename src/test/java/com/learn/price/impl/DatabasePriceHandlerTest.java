package com.learn.price.impl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.learn.daos.price.PriceRepository;
import com.learn.price.PriceHandler;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class DatabasePriceHandlerTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceHandler nextHandler;

    @InjectMocks
    private DatabasePriceHandler dbHandler;


    @Before
    public void setUp() {
//        dbHandler = new DatabasePriceHandler();
//        dbHandler.setNextHandler(null);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchPriceFromDatabase() {
        when(priceRepository.findPriceByProductId("productFromDb")).thenReturn(200.0);

        Double price = dbHandler.fetchPrice("productFromDb");
        assertNotNull(price);
        assertEquals(200.0, price, 0.0);
    }

    @Test
    public void testFetchPriceNotFoundInDatabase() {
        when(priceRepository.findPriceByProductId("productFromApi")).thenReturn(null);
        when(nextHandler.fetchPrice(anyString())).thenReturn(null);
        Double price = dbHandler.fetchPrice("productFromApi");
        assertNull(price);
    }

    @Test
    public void testFetchPriceFoundInAPI() {
        when(priceRepository.findPriceByProductId("productFromApi")).thenReturn(null);
        when(nextHandler.fetchPrice(anyString())).thenReturn(301d);
        Double price = dbHandler.fetchPrice("productFromApi");
        assertNotNull(price);
    }

    @Test
    public void testFetchPriceNotFoundInAPI() {
        dbHandler.setNextHandler(null);
        when(priceRepository.findPriceByProductId("productFromApi")).thenReturn(null);
        when(nextHandler.fetchPrice(anyString())).thenReturn(301d);
        Double price = dbHandler.fetchPrice("productFromApi");
        assertNull(price);
    }
}
