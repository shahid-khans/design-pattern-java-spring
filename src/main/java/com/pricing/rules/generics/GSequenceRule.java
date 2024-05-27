package com.pricing.rules.generics;

import com.pricing.rules.PricingContext;
import com.pricing.rules.Rule;

public class GSequenceRule implements Rule<PricingContext, Rule<PricingContext, Double>> {
    private boolean enable;
    private double discount ;
    private Rule<PricingContext, Double> _this, _that;

    public GSequenceRule(boolean enable, Rule<PricingContext, Double> _this, Rule<PricingContext, Double> _that) {
        this.enable = enable;
        this._this = _this;
        this._that = _that;
    }
    @Override
    public boolean evaluate(PricingContext context) {
        return this.enable;
    }


    @Override
    public Rule<PricingContext, Double> execute(PricingContext context) {
        System.out.println(this.getClass().getName());
        if (context.getCustomer().isLoyaltyMember()) {
            return _this;
        } else {
            return _that;
        }
    }
}
