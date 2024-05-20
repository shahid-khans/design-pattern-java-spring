package com.learn.price.impl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.learn.daos.price.PriceRepository;
import com.learn.price.PriceHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class ApiPriceHandlerTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceHandler nextHandler;

    @InjectMocks
    private ApiPriceHandler dbHandler;


    @Before
    public void setUp() {
//        dbHandler = new DatabasePriceHandler();
//        dbHandler.setNextHandler(null);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchPriceNotFoundInDatabase() {
        when(priceRepository.findPriceByProductId("productFromApi")).thenReturn(300d);
        when(nextHandler.fetchPrice(anyString())).thenReturn(null);
        Double price = dbHandler.fetchPrice("productFromApi");
        assertEquals(300.0, price, 0.0);
    }

}
