package com.pricing.rules;

public class SeasonalDiscountRule implements PricingRule {
    private boolean enable;
    private double discount ;

    public SeasonalDiscountRule(boolean enable, double discount) {
        this.enable = enable;
        this.discount = discount;
    }
    @Override
    public boolean evaluate(PricingContext context) {
        return enable;
    }

    @Override
    public double calculatePrice(PricingContext context) {
        System.out.println(this.getClass().getName());
        context.setNetPrice(context.getNetPrice() * (1-this.discount/100));
        return context.getNetPrice();
    }
}
