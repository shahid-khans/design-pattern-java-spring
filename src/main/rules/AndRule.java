package com.pricing.rules;

import java.util.List;

class AndRule implements CompoundRule {
    private List<PricingRule> rules;

    public AndRule(List<PricingRule> rules) {
        this.rules = rules;
    }

    @Override
    public List<PricingRule> getRules() {
        return rules;
    }

    @Override
    public boolean evaluate(PricingContext context) {
        for (PricingRule rule : rules) {
            if (!rule.evaluate(context)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public double calculatePrice(PricingContext context) {
        double price = context.getProduct().getBasePrice() * context.getQuantity();
        for (PricingRule rule : rules) {
            price = rule.calculatePrice(context);
        }
        return price;
    }
}