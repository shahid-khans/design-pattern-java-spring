package com.learn.services.price;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.learn.price.PriceHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.mockito.invocation.InvocationOnMock;
public class PriceServiceTest {


    @Mock
    private PriceHandler priceHandler;

    @InjectMocks
    private PriceService priceService;

    @Before
    public void setUp() {
        priceService = new PriceService(priceHandler);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testFetchPriceFromRedis() {
//        when(priceHandler.fetchPrice("productFromRedis")).thenReturn(100d);
        when(priceHandler.fetchPrice(anyString())).thenAnswer(
            new Answer() {
                public Object answer(InvocationOnMock invocation) {
    
                    System.out.println(">>> priceHandler invocation "+ invocation.getArgument(0));
                    String productId = invocation.getArgument(0);
                    switch (productId) {
                        case "productFromRedis": return 100d;
                        case "productFromDb": return 200d;
                        case "productFromApi": return 300d;
                        default: return null;
                    }
    
                }
        }
    
        );
        Double price = priceService.getPrice("productFromRedis");
        assertNotNull(price);
        assertEquals(100.0, price, 0.0);
        verify(priceHandler, times(1)).fetchPrice("productFromRedis");
        verify(priceHandler, never()).fetchPrice("productFromDb");
        verify(priceHandler, never()).fetchPrice("productFromApi");
    }

    @Test
    public void testFetchPriceFromDatabase() {
        when(priceHandler.fetchPrice(anyString())).thenAnswer(
            new Answer() {
                public Object answer(InvocationOnMock invocation) {
    
                    System.out.println(">>> priceHandler invocation "+ invocation.getArgument(0));
                    String productId = invocation.getArgument(0);
                    switch (productId) {
                        case "productFromRedis": return 100d;
                        case "productFromDb": return 200d;
                        case "productFromApi": return 300d;
                        default: return null;
                    }
    
                }
        }
    
        );

        Double price = priceService.getPrice("productFromDb");
        assertNotNull(price);
        assertEquals(200.0, price, 0.0);
        verify(priceHandler, times(1)).fetchPrice("productFromDb");
        verify(priceHandler, never()).fetchPrice("productFromApi");
    }
    @Test
    public void testFetchPriceFromApi() {
        when(priceHandler.fetchPrice(anyString())).thenAnswer( new Answer() {
            public Object answer(InvocationOnMock invocation) {

                System.out.println(">>> priceHandler invocation "+ invocation.getArgument(0));
                String productId = invocation.getArgument(0);
                switch (productId) {
                    case "productFromRedis": return 100d;
                    case "productFromDb": return 200d;
                    case "productFromApi": return 300d;
                    default: return null;
                }

            }
    }

    );

        Double price = priceService.getPrice("productFromApi");
        assertNotNull(price);
        assertEquals(300.0, price, 0.0);
        verify(priceHandler, times(1)).fetchPrice("productFromApi");
    }

    @Test
    public void testFetchPriceNotFound() {
//        when(priceHandler.fetchPrice(anyString())).thenReturn(null);

        when(priceHandler.fetchPrice(anyString())).thenAnswer(

        new Answer() {
                public Object answer(InvocationOnMock invocation) {

                    System.out.println(">>> priceHandler invocation "+ invocation.getArgument(0));
                    String productId = invocation.getArgument(0);
                    switch (productId) {
                        case "productFromRedis": return 100d;
                        case "productFromDb": return 200d;
                        case "productFromApi": return 300d;
                        default: return null;
                    }

                }
        }

        );

        Double price = priceService.getPrice("nonExistentProduct");
        assertNull(price);
        verify(priceHandler, times(1)).fetchPrice("nonExistentProduct");
        verify(priceHandler, times(1)).fetchPrice("nonExistentProduct");
        verify(priceHandler, times(1)).fetchPrice("nonExistentProduct");
    }
}
