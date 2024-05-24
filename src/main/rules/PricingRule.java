package com.pricing.rules;

interface PricingRule {
    boolean evaluate(PricingContext context);
    double calculatePrice(PricingContext context);
}