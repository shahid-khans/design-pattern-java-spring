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


public class ApiPriceHandlerTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceHandler nextHandler;

    @InjectMocks
    private ApiPriceHandler apiHandler;


    @Before
    public void setUp() {
//        apiHandler = new ApiPriceHandler();
//        apiHandler.setNextHandler(null);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchPriceFromAPI() {
        when(priceRepository.findPriceByProductId("productFromDb")).thenReturn(100.0);

        Double price = apiHandler.fetchPrice("productFromDb");
        assertNotNull(price);
        assertEquals(100.0, price, 0.0);
    }


    @Test
    public void testFetchPriceFoundInAPI() {
        when(priceRepository.findPriceByProductId("productFromApi")).thenReturn(null);
        when(nextHandler.fetchPrice(anyString())).thenReturn(301d);
        Double price = apiHandler.fetchPrice("productFromApi");
        assertNotNull(price);
    }

    @Test
    public void testFetchPriceNotFoundInAPI() {
        apiHandler.setNextHandler(null);
        when(priceRepository.findPriceByProductId("productFromApi")).thenReturn(null);
        when(nextHandler.fetchPrice(anyString())).thenReturn(301d);
        Double price = apiHandler.fetchPrice("productFromApi");
        assertNull(price);
    }
}
