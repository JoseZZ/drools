package com.training.java.drools.rulebook.rules;


import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;

public class HelloWorldWithFactsRule {
    public RuleBook<Object> defineHelloWorldRules() {
        return RuleBookBuilder.create()
            .addRule(rule -> rule.withFactType(String.class)
                .when(f -> f.containsKey("hello"))
                .using("hello")
                .then(System.out::print))
            .addRule(rule -> rule.withFactType(String.class)
                .when(f -> f.containsKey("world"))
                .using("world")
                .then(System.out::println))
            .build();
    }
}
