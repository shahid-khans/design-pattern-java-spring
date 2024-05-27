package com.pricing.rules.generics;

import com.pricing.rules.Rule;

import java.util.List;

public interface GCompoundRule<I,O> extends Rule<I,O> {
    List<Rule<I,O>> getRules();
}