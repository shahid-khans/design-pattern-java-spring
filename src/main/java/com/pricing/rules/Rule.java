package com.pricing.rules;

public interface Rule<I,O> {
    boolean evaluate(I context);
    O execute(I context);
}
