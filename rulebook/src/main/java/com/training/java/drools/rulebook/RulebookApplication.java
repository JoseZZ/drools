package com.training.java.drools.rulebook;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.training.java.drools.rulebook.beans.Address;
import com.training.java.drools.rulebook.beans.Address.AddressBuilder;
import com.training.java.drools.rulebook.beans.ApplicantBean;
import com.training.java.drools.rulebook.rules.HelloWorldWithFactsRule;
import com.training.java.drools.rulebook.rules.HelloWorldWithoutFactsRule;
import com.training.java.drools.rulebook.rules.HomeLoanRateRuleBook;
import com.training.java.drools.rulebook.rules.HomeLoanRateRuleBookWithoutBean;
import com.training.java.drools.rulebook.rules.PostalCodeValidatorRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RulebookApplication {

    public static void main(String[] args) {

        SpringApplication.run(RulebookApplication.class, args);

        // Test HelloWorldWithoutFactsRule
        HelloWorldWithoutFactsRule withoutFactsRule = new HelloWorldWithoutFactsRule();
        withoutFactsRule.defineHelloWorldRules().run(new FactMap<>());

        // Test HelloWorldWithFactsRule
        HelloWorldWithFactsRule withFactsRule = new HelloWorldWithFactsRule();
        NameValueReferableMap factMap = new FactMap();
        factMap.setValue("hello", "Hola ");
        factMap.setValue("world", "Mundo");
        withFactsRule.defineHelloWorldRules().run(factMap);

        // Test HomeLoanRateRuleBook
        RuleBook homeLoanRateRuleBook = RuleBookBuilder.create(HomeLoanRateRuleBook.class).withResultType(Double.class)
            .withDefaultResult(4.5)
            .build();
        NameValueReferableMap facts = new FactMap();
        facts.setValue("applicant", new ApplicantBean(650, 20000.0, true));
        homeLoanRateRuleBook.run(facts);

        homeLoanRateRuleBook.getResult().ifPresent(result -> System.out.println("Applicant qualified for the following rate: " + result));

        // Test HomeLoanRateRuleBookWithoutBean
        RuleBook ruleBook = RuleBookBuilder.create(HomeLoanRateRuleBookWithoutBean.class).withResultType(Double.class)
            .withDefaultResult(4.5)
            .build();

        NameValueReferableMap referableMap = new FactMap();
        referableMap.setValue("Credit Score", 650);
        referableMap.setValue("Cash on Hand", 20000);
        referableMap.setValue("First Time Homebuyer", true);

        ruleBook.run(referableMap);

        ruleBook.getResult().ifPresent(result -> System.out.println("Applicant qualified for the following rate: " + result));

        // Test PostalCodeValidatorRule
        RuleBook validatorRule = RuleBookBuilder.create(PostalCodeValidatorRule.class).withResultType(Boolean.class)
                                    .withDefaultResult(Boolean.FALSE)
                                    .build();
        NameValueReferableMap validatorFactsTrue = new FactMap();
        validatorFactsTrue.setValue("address", Address.builder().country("ES").region("M").zipCode("28080").build());
        validatorRule.run(validatorFactsTrue);

        validatorRule.getResult().ifPresent(result -> System.out.println("¿El codigo postal 28080 pertenece a Madrid?: " + result));

        NameValueReferableMap validatorFactsFalse = new FactMap();
        validatorFactsFalse.setValue("address", Address.builder().country("ES").region("ZA").zipCode("28080").build());
        validatorRule.run(validatorFactsFalse);

        validatorRule.getResult().ifPresent(result -> System.out.println("¿El codigo postal 28080 pertenece a Zaragoza?: " + result));
    }

}
