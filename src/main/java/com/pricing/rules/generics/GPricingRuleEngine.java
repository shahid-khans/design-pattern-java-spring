package com.pricing.rules.generics;

import com.pricing.rules.PricingContext;
import com.pricing.rules.Rule;

import java.util.List;

public class GPricingRuleEngine {
    private List<Rule<PricingContext,Double>> rules;

    public GPricingRuleEngine (List<Rule<PricingContext,Double>> rules) {
        this.rules = rules;
    }

    public double calculatePrice(PricingContext context) {
        double basePrice = context.getProduct().getBasePrice() * context.getQuantity();
        double price = basePrice;

        for (Rule<PricingContext,Double> rule : rules) {

            if (rule instanceof GCompoundRule) {
                System.out.println(">> execute compound rule \n" + rule.getClass());
                if (rule.evaluate(context)) {
                    price = rule.execute(context);
                }
            } else if (rule.evaluate(context)) {
                System.out.println(">>> execute single rule \n" + rule.getClass());
                price = rule.execute(context);
            }
        }

        return price;
    }
}
