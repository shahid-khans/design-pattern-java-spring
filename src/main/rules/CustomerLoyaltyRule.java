package com.pricing.rules;

/**
 * if loyalCustomer DISC 20%
 */
class CustomerLoyaltyRule implements PricingRule {
    @Override
    public boolean evaluate(PricingContext context) {
        return context.getCustomer().isLoyaltyMember();
    }

    @Override
    public double calculatePrice(PricingContext context) {
        System.out.println(this.getClass().getName());
        context.setNetPrice(context.getNetPrice() * (1-0.20));
        return context.getNetPrice(); // 20% discount for loyalty members
    }
}