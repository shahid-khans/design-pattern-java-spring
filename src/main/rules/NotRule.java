package com.pricing.rules;

import java.util.Collections;
import java.util.List;

class NotRule implements CompoundRule {
    private PricingRule rule;

    public NotRule(PricingRule rule) {
        this.rule = rule;
    }

    @Override
    public List<PricingRule> getRules() {
        return Collections.singletonList(rule);
    }

    @Override
    public boolean evaluate(PricingContext context) {
        return !rule.evaluate(context);
    }

    @Override
    public double calculatePrice(PricingContext context) {
        if (rule.evaluate(context)) {
            return context.getProduct().getBasePrice() * context.getQuantity();
        } else {
            return rule.calculatePrice(context);
        }
    }
}