package com.pricing.rules;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PricingApplication {
    public static void main(String[] args) {
        Product product = new Product(10); // Base price = $100
        Customer customer = new Customer(true); // Loyalty member
        customer.setRRPRef(true);

        List<PricingRule> rules = new ArrayList<>();
        // // Running sequentially
//        rules.add(new FlatPricingRule());
//        rules.add(new QuantityDiscountRule());
//        rules.add(new CustomerLoyaltyRule());
//        rules.add(new VolumeDiscountRule(true, 10));
//        rules.add(new SeasonalDiscountRule(true, 10));

        // Scenario 1: EITHER Quantity discount OR customer loyalty, First true rule in Sequence  will apply
//        List<PricingRule> orRules1 = new ArrayList<>();
//        orRules1.add(new QuantityDiscountRule());
//        orRules1.add(new CustomerLoyaltyRule());
//        rules.add(new OrRule(orRules1));

//     Scenario 1.1:  Quantity discount AND customer loyalty, Both should be true to apply all true or none
//        List<PricingRule> andRules11 = new ArrayList<>();
//        andRules11.add(new QuantityDiscountRule());
//        andRules11.add(new CustomerLoyaltyRule());
//        rules.add(new AndRule(andRules11));
//
////         Scenario 2: (Quantity discount AND customer loyalty) OR volume discount
//        List<PricingRule> andRules = new ArrayList<>();
//        andRules.add(new QuantityDiscountRule());
//        andRules.add(new CustomerLoyaltyRule());
//        AndRule andRule = new AndRule(andRules);
////
//        List<PricingRule> orRules2 = new ArrayList<>();
//        orRules2.add(andRule);
//        orRules2.add(new VolumeDiscountRule(true, 10));
//        rules.add(new OrRule(orRules2));

//        //         Scenario 2.1: (Quantity discount AND customer loyalty) AND volume discount
//        List<PricingRule> andRules = new ArrayList<>();
//        andRules.add(new QuantityDiscountRule());
//        andRules.add(new CustomerLoyaltyRule());
//        AndRule andRule = new AndRule(andRules);
////
//        List<PricingRule> andRules2 = new ArrayList<>();
//        andRules2.add(andRule);
//        andRules2.add(new VolumeDiscountRule(true, 10));
//        rules.add(new AndRule(andRules2));
//
        // Scenario 3: NOT seasonal discount AND (quantity discount OR customer loyalty)
        List<PricingRule> orRules3 = new ArrayList<>();
        orRules3.add(new QuantityDiscountRule());
        orRules3.add(new CustomerLoyaltyRule());
        OrRule orRule3 = new OrRule(orRules3);
//
        List<PricingRule> andRules2 = new ArrayList<>();
        andRules2.add(new NotRule(new SeasonalDiscountRule(true, 10)));
        andRules2.add(orRule3);
        rules.add(new AndRule(andRules2));

        PricingContext context = new PricingContext(product, 100, customer);
        PricingRuleEngine ruleEngine = new PricingRuleEngine(rules);
        double price = ruleEngine.calculatePrice(context);
        System.out.println("Final price: $" + price);
    }
}