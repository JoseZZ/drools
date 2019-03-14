package com.training.java.drools.rulebook.beans.spain;

import static com.deliveredtechnologies.rulebook.model.RuleChainActionType.ERROR_ON_FAILURE;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;
import com.deliveredtechnologies.rulebook.spring.RuleBean;
import com.training.java.drools.rulebook.beans.Address;

@RuleBean
@Rule(order = 1, ruleChainAction = ERROR_ON_FAILURE)
public class CountrySpainRule {

    private final String COUNTRY_SPAIN= "ES";

    @Given("address")
    private Address address;

    // Si se define, el resultado debe ser del mismo tipo para todas las reglas dentro del RuleBook
    // Todas las reglas dentro del RuleBook podrán modificar este resultado
    @Result
    private String resultado;

    @When
    public boolean when(){
        boolean validacion = !address.getCountry().equals(COUNTRY_SPAIN);
        System.out.println("¿El pais es España?: " + !validacion);
        return validacion;
    }

    @Then
    public RuleState then() {
        resultado = "KO";
        return RuleState.BREAK;
    }
}
