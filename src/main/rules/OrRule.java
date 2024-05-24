package com.pricing.rules;

import java.util.List;

// OR compound rule
class OrRule implements CompoundRule {
    private List<PricingRule> rules;

    public OrRule(List<PricingRule> rules) {
        this.rules = rules;
    }

    @Override
    public List<PricingRule> getRules() {
        return rules;
    }

    @Override
    public boolean evaluate(PricingContext context) {
        for (PricingRule rule : rules) {
            if (rule.evaluate(context)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double calculatePrice(PricingContext context) {
        double price = context.getProduct().getBasePrice() * context.getQuantity();
        for (PricingRule rule : rules) {
            if (rule.evaluate(context)) {
                price = rule.calculatePrice(context);
                break; // Exit the loop after the first rule that matches
            }
        }
        return price;
    }
}