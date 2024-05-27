package com.pricing.rules;

import java.math.BigDecimal;

public class PricingContext {
    private Product product;
    private int quantity;
    private Customer customer;
    private double netPrice ;
    public PricingContext(Product product, int quantity, Customer customer) {
        this.product = product;
        this.quantity = quantity;
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getNetPrice() {
        if(netPrice == 0) return product.getBasePrice() * getQuantity();
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }
}