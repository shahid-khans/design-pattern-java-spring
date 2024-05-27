package com.pricing.rules.generics;

import com.pricing.rules.PricingContext;
import com.pricing.rules.Rule;

import java.util.List;

public class GAndRule implements GCompoundRule<PricingContext, Double> {
    private List<Rule<PricingContext, Double>> rules;

    public GAndRule(List<Rule<PricingContext, Double>> rules) {
        this.rules = rules;
    }

    @Override
    public List<Rule<PricingContext, Double>> getRules() {
        return rules;
    }

    @Override
    public boolean evaluate(PricingContext context) {
        for (Rule<PricingContext, Double> rule : rules) {
            if (!rule.evaluate(context)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Double execute(PricingContext context) {
        Double price = context.getProduct().getBasePrice() * context.getQuantity();
        for (Rule<PricingContext, Double> rule : rules) {
            price = rule.execute(context);
        }
        return price;
    }
}