package com.training.java.drools.spainvalidator;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.training.java.drools.spainvalidator.model.Address;
import com.training.java.drools.spainvalidator.model.Propuesta;
import com.training.java.drools.spainvalidator.service.AddressValidatorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SpainValidatorApplication.class)
public class ZipCodeTest {

    @Autowired
    AddressValidatorService validatorService;

    @Before
    public void setup(){

    }

    @Test
    public void whenZipCodeBelongsToMonacoAndCountryIsMonaco_thenValidationIsOK(){
        Address address = new Address();
        address.setPais("MA");
        address.setZipcode("29087");
        Propuesta propuesta = validatorService.valoraDireccion(address);
        assertNotNull(propuesta);
        assertEquals(propuesta.getValoracion(), Long.valueOf(100L));
        assertEquals(propuesta.getPropuesta(), null);
    }

}
