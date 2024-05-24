package com.pricing.rules;

import java.util.List;

class PricingRuleEngine {
    private List<PricingRule> rules;

    public PricingRuleEngine(List<PricingRule> rules) {
        this.rules = rules;
    }

    public double calculatePrice(PricingContext context) {
        double basePrice = context.getProduct().getBasePrice() * context.getQuantity();
        double price = basePrice;

        for (PricingRule rule : rules) {
            if (rule instanceof CompoundRule) {
                CompoundRule compoundRule = (CompoundRule) rule;
                if (compoundRule.evaluate(context)) {
                    price = compoundRule.calculatePrice(context);
                }
            } else if (rule.evaluate(context)) {
                price = rule.calculatePrice(context);
            }
        }

        return price;
    }
}
