package com.pricing.rules.generics;
import com.pricing.rules.PricingContext;
import com.pricing.rules.Rule;

import java.util.List;

/**
 * QTY > 10 and DISC 10%
 */
public class GQuantityDiscountRule implements Rule<PricingContext, Double> {


    @Override
    public boolean evaluate(PricingContext context) {
        return context.getQuantity() >= 10;

    }

    @Override
    public Double execute(PricingContext context) {
        context.setNetPrice(context.getNetPrice() * 0.9); // 10% discount for quantities  over 10
        System.out.println(this.getClass().getName()+ " " + context.getNetPrice());

        return context.getNetPrice();

    }
}
