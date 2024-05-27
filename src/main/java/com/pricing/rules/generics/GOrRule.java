package com.pricing.rules.generics;

import com.pricing.rules.PricingContext;
import com.pricing.rules.Rule;

import java.util.List;

public class GOrRule implements GCompoundRule<PricingContext, Double> {
    private List<Rule<PricingContext, Double>> rules;

    public GOrRule(List<Rule<PricingContext, Double>> rules) {
        this.rules = rules;
    }

    @Override
    public List<Rule<PricingContext, Double>> getRules() {
        return rules;
    }

    @Override
    public boolean evaluate(PricingContext context) {
        for (Rule<PricingContext, Double> rule : rules) {
            if (rule.evaluate(context)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Double execute(PricingContext context) {
        Double price = context.getProduct().getBasePrice() * context.getQuantity();
        for (Rule<PricingContext, Double> rule : rules) {
            price = rule.execute(context);
	    break;
        }
        return price;
    }
}