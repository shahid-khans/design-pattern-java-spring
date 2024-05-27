package com.pricing.rules;

public class Customer {
    private boolean isLoyaltyMember;
    private boolean isRRPRef;


    public Customer(boolean isLoyaltyMember) {
        this.isLoyaltyMember = isLoyaltyMember;
    }

    public boolean isLoyaltyMember() {
        return isLoyaltyMember;
    }

    public boolean isRRPRef() {
        return isRRPRef;
    }

    public void setRRPRef(boolean RRPRef) {
        isRRPRef = RRPRef;
    }
}