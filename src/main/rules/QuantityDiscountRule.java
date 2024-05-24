package com.pricing.rules;

/**
 * QTY > 10 and DISC 10%
 */
class QuantityDiscountRule implements PricingRule {
    @Override
    public boolean evaluate(PricingContext context) {
        return context.getQuantity() >= 10;
    }

    @Override
    public double calculatePrice(PricingContext context) {
        System.out.println(this.getClass().getName());
        context.setNetPrice(context.getNetPrice() * 0.9); // 10% discount for quantities  over 10
        return context.getNetPrice();

    }
}


