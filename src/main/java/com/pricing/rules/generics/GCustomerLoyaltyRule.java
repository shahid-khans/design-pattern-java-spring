package com.pricing.rules.generics;

import com.pricing.rules.PricingContext;
import com.pricing.rules.Rule;

/**
 * if loyalCustomer DISC 20%
 */
public class GCustomerLoyaltyRule implements Rule<PricingContext, Double> {
    @Override
    public boolean evaluate(PricingContext context) {
        return context.getCustomer().isLoyaltyMember();
    }

    @Override
    public Double execute(PricingContext context) {
        context.setNetPrice(context.getNetPrice() * (1-0.20));
        System.out.println(this.getClass().getName() + " " + context.getNetPrice());

        return context.getNetPrice(); // 20% discount for loyalty members
    }
}