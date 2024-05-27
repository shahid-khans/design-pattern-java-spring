package com.pricing.rules.generics;

import com.pricing.rules.PricingContext;
import com.pricing.rules.Rule;

public class GFlatPricingRule implements Rule<PricingContext, Double> {
    @Override
    public boolean evaluate(PricingContext context) {
        return true; // Always applicable
    }

    @Override
    public Double execute(PricingContext context) {
        context.setNetPrice(context.getProduct().getBasePrice() * context.getQuantity());
        System.out.println(this.getClass().getName()+ " " + context.getNetPrice());

        return context.getNetPrice();
    }
}