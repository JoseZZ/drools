package com.training.java.drools.rulebook.rules;

import com.deliveredtechnologies.rulebook.lang.RuleBuilder;
import com.deliveredtechnologies.rulebook.model.rulechain.cor.CoRRuleBook;
import com.training.java.drools.rulebook.beans.Address;
import java.util.HashMap;

/**
 * @author <a href="changeme@ext.inditex.com">Jose Gonzalez</a>
 */
public class PostalCodeValidatorRule extends CoRRuleBook<Boolean> {

    private static final String SPAIN = "ES";

    private final HashMap<String, String> codigos = new HashMap<String, String>(){{
        put("VI", "01");
        put("AB", "02");
        put("A" , "03");
        put("AL", "04");
        put("AV", "05");
        put("BA", "06");
        put("PM", "07");
        put("B" , "08");
        put("BU", "09");
        put("CC", "10");
        put("CA", "11");
        put("CS", "12");
        put("CR", "13");
        put("CO", "14");
        put("C" , "15");
        put("CU", "16");
        put("GE", "17");
        put("GR", "18");
        put("GU", "19");
        put("SS", "20");
        put("H" , "21");
        put("HU", "22");
        put("J" , "23");
        put("LE", "24");
        put("L" , "25");
        put("LO", "26");
        put("LU", "27");
        put("M" , "28");
        put("MA", "29");
        put("MU", "30");
        put("NA", "31");
        put("OR", "32");
        put("O" , "33");
        put("P" , "34");
        put("GC", "35");
        put("PO", "36");
        put("SA", "37");
        put("TF", "38");
        put("S" , "39");
        put("SG", "40");
        put("SE", "41");
        put("SO", "42");
        put("T" , "43");
        put("TE", "44");
        put("TO", "45");
        put("V" , "46");
        put("VA", "47");
        put("BI", "48");
        put("ZA", "49");
        put("Z" , "50");
        put("CE", "51");
        put("ML", "52");
    }};

    @Override
    public void defineRules() {
        // Regla para validar una direccion española
        addRule(RuleBuilder.create().withFactType(Address.class).withResultType(Boolean.class)
            .when(facts ->
                    facts.getOne().getCountry().equals(SPAIN) &&
                    codigos.containsKey(facts.getOne().getRegion()) &&
                    codigos.get(facts.getOne().getRegion()).equals(facts.getOne().getZipCode().substring(0, 2)))
            .then((facts, result) -> result.setValue(Boolean.TRUE))
            .stop()
            .build());
    }
}
