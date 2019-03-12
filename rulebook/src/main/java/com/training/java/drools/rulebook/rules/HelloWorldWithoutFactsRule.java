package com.training.java.drools.rulebook.rules;


import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;

public class HelloWorldWithoutFactsRule {
    public RuleBook<Object> defineHelloWorldRules() {
        return RuleBookBuilder
            .create()
            .addRule(rule -> rule.withNoSpecifiedFactType()
                .then(f -> System.out.print("Hello ")))
            .addRule(rule -> rule.withNoSpecifiedFactType()
                .then(f -> System.out.println("World")))
            .build();
    }
}
