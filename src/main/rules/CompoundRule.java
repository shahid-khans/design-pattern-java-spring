package com.pricing.rules;

import java.util.List;

interface CompoundRule extends PricingRule {
    List<PricingRule> getRules();
}