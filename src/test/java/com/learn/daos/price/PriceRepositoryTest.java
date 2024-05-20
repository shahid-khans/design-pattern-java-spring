package com.learn.daos.price;
import static org.mockito.Mockito.*;

import com.learn.daos.price.impl.APIDao;
import com.learn.daos.price.impl.DbDao;
import com.learn.daos.price.impl.PriceRepositoryImpl;
import com.learn.daos.price.impl.RedisDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class PriceRepositoryTest {

    @Mock
    DbDao dbDao;

    @InjectMocks
    private PriceRepository priceRepository;

    @Before
    public void setup() {
        priceRepository = new PriceRepositoryImpl();


        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testFindPriceByProductIdRedis() {
        when(dbDao.findPriceByProductId("productFromRedis")).thenReturn(101d);
        Double pricer = priceRepository.findPriceByProductId("productFromRedis");
        Assert.assertNotNull("price", pricer);
        Assert.assertEquals(101, pricer, 0);

    }
    @Test
    public void testFindPriceByProductIdDB() {
        when(dbDao.findPriceByProductId("productFromDb")).thenReturn(201d);
        Double priced = priceRepository.findPriceByProductId("productFromDb");
        Assert.assertNotNull("price", priced);
        Assert.assertEquals(201, priced, 0);

    }
    @Test
    public void testFindPriceByProductIdAPI() {
        when(dbDao.findPriceByProductId("productFromApi")).thenReturn(301d);
        Double pricea = priceRepository.findPriceByProductId("productFromApi");
        System.out.println(pricea);
        Assert.assertNotNull("price", pricea);
        Assert.assertEquals(301, pricea, 0);
    }
    @Test
    public void testFindPriceByProductIdNull() {
        when(dbDao.findPriceByProductId(anyString())).thenReturn(null);
        Double pricen = priceRepository.findPriceByProductId("");
        System.out.println(pricen);
        Assert.assertNull(pricen);
    }
}
