package com.pricing.rules.generics;

import com.pricing.rules.PricingContext;
import com.pricing.rules.Rule;

public class GSeasonalDiscountRule implements Rule<PricingContext, Double> {
    private boolean enable;
    private double discount ;
 public GSeasonalDiscountRule (boolean enable, double discount) {
        this.enable = enable;
        this.discount = discount;
    }
    @Override
    public boolean evaluate(PricingContext context) {
        return enable;
    }

    @Override
    public Double execute(PricingContext context) {
        System.out.println(this.getClass().getName());
        context.setNetPrice(context.getNetPrice() * (1-this.discount/100));
        return context.getNetPrice();
    }
}