package com.pricing.rules.generics;

import com.pricing.rules.PricingContext;
import com.pricing.rules.Rule;

import java.util.Collections;
import java.util.List;

public class GNotRule implements GCompoundRule<PricingContext, Double> {
    private Rule<PricingContext, Double> rule;

    public GNotRule(Rule<PricingContext, Double> rule) {
        this.rule = rule;
    }

    @Override
    public List<Rule<PricingContext, Double>> getRules() {
        return Collections.singletonList(rule);
    }

    @Override
    public boolean evaluate(PricingContext context) {
        return !rule.evaluate(context);
    }

    @Override
    public Double execute(PricingContext context) {
        if (rule.evaluate(context)) {
            return context.getProduct().getBasePrice() * context.getQuantity();
        } else {
            return rule.execute(context);
        }
    }
}