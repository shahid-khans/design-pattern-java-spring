package com.pricing.rules.generics;

import com.pricing.rules.Customer;
import com.pricing.rules.PricingContext;
import com.pricing.rules.Product;
import com.pricing.rules.Rule;

import java.util.ArrayList;
import java.util.List;

public class GPricingApplication {
    public static void main(String[] args) {
        Product product = new Product(10); // Base price = $100
        Customer customer = new Customer(false); // Loyalty member
        customer.setRRPRef(true);
        PricingContext context = new PricingContext(product, 100, customer);

        List<Rule<PricingContext, Double>> rules = new ArrayList<>();
         // Running sequentially
        Rule<PricingContext, Double> gFlatPricingRule = new GFlatPricingRule();
        Rule<PricingContext, Double> gQuantityDiscountRule = new GQuantityDiscountRule();
        Rule<PricingContext, Double> gCustomerLoyaltyRule = new GCustomerLoyaltyRule();
//        rules.add(new GFlatPricingRule());
//        rules.add(new GQuantityDiscountRule());
//        rules.add(new GCustomerLoyaltyRule());
//        rules.add(new GVolumeDiscountRule(true, 10));
//        rules.add(new GSeasonalDiscountRule(true, 10));
        rules.add(gFlatPricingRule);
        GSequenceRule gSequenceRule = new GSequenceRule(false, gCustomerLoyaltyRule, gQuantityDiscountRule);
        GSequenceRule gSequenceRule2 = new GSequenceRule(false, gSequenceRule.execute(context), gQuantityDiscountRule);

        rules.add(gSequenceRule2.execute(context));

//        // Scenario 1: EITHER Quantity discount OR customer loyalty, First true rule in Sequence  will apply
//        List<Rule<PricingContext, Double>> orRules1 = new ArrayList<>();
//        orRules1.add(new GQuantityDiscountRule());
//        orRules1.add(new GCustomerLoyaltyRule());
//        rules.add(new GOrRule(orRules1));

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
//        List<Rule<PricingContext, Double>> andRules = new ArrayList<>();
//        andRules.add(new GQuantityDiscountRule());
//        andRules.add(new GCustomerLoyaltyRule());
//        GAndRule andRule = new GAndRule(andRules);
////
//        List<Rule<PricingContext, Double>> andRules2 = new ArrayList<>();
//        andRules2.add(andRule);
//        andRules2.add(new GVolumeDiscountRule(true, 10));
//        rules.add(new GAndRule(andRules2));
//
////         Scenario 3: (quantity discount OR customer loyalty) AND NOT seasonal discount
//        List<Rule<PricingContext, Double>> orRules1 = new ArrayList<>();
//        orRules1.add(new GQuantityDiscountRule());
//        orRules1.add(new GCustomerLoyaltyRule());
//        GOrRule orRule3 = new GOrRule(orRules1);
////
//        List<Rule<PricingContext, Double>> andRules2 = new ArrayList<>();
//        andRules2.add(new GNotRule(new GSeasonalDiscountRule(false, 10)));
//        andRules2.add(orRule3);
//        rules.add(new GAndRule(andRules2));

        GPricingRuleEngine ruleEngine = new GPricingRuleEngine(rules);
        double price = ruleEngine.calculatePrice(context);
        System.out.println("Final price: $" + price);
    }
}