package com.pricing.rules;

class FlatPricingRule implements PricingRule {
    @Override
    public boolean evaluate(PricingContext context) {
        return true; // Always applicable
    }

    @Override
    public double calculatePrice(PricingContext context) {
        System.out.println(this.getClass().getName());
        context.setNetPrice(context.getProduct().getBasePrice() * context.getQuantity());
        return context.getNetPrice();
    }
}