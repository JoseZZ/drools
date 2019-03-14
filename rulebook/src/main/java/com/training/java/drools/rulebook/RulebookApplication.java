package com.training.java.drools.rulebook;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.deliveredtechnologies.rulebook.spring.SpringAwareRuleBookRunner;
import com.training.java.drools.rulebook.beans.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RulebookApplication implements CommandLineRunner {

    @Autowired
    private RuleBook ruleBook;

    @Bean
    public RuleBook ruleBook() {
        RuleBook ruleBook = new SpringAwareRuleBookRunner("com.training.java.drools.rulebook.beans.spain");
        return ruleBook;
    }

    public static void main(String[] args) {
        SpringApplication.run(RulebookApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println("--- Test de reglas con POJOS ---");

        NameValueReferableMap<Address> facts = new FactMap<>();
        Address addressApplicant = Address.builder().country("ES").region("M").zipCode("28080").build();
        facts.setValue("address", addressApplicant);
        System.out.println("\nEsta prueba deberia ser correcta -->");
        ruleBook().setDefaultResult("KO");
        ruleBook.run(facts);
        ruleBook.getResult().ifPresent(result -> System.out.println("Resultado de la validacion de la dirección " + addressApplicant + ": " + result));

        // Codigo postal y provincia no coinciden
        // El RuleBook persiste el estado entre las distintas reglas usando los facts.
        Address addressApplicant2 = Address.builder().country("ES").region("ZA").zipCode("28080").build();
        facts.setValue("address", addressApplicant2);
        System.out.println("\nEsta prueba deberia fallar porque no coinciden el codigo postal y la provincia -->");
        ruleBook.run(facts);
        ruleBook.getResult().ifPresent(result -> System.out.println("Resultado de la validacion de la dirección " + addressApplicant2 + ": " + result));

        // El pais no es España
        Address addressApplicant3 = Address.builder().country("US").region("M").zipCode("28080").build();
        facts.setValue("address", addressApplicant3);
        System.out.println("\nEsta prueba deberia fallar porque el pais no es España-->");
        ruleBook.run(facts);
        ruleBook.getResult().ifPresent(result -> System.out.println("Resultado de la validacion de la dirección " + addressApplicant3 + ": " + result));

    }
}
