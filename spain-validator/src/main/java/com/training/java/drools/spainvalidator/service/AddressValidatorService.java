package com.training.java.drools.spainvalidator.service;

import com.training.java.drools.spainvalidator.model.Address;
import com.training.java.drools.spainvalidator.model.Propuesta;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressValidatorService {

    @Autowired
    private KieContainer kContainer;


    public Propuesta valoraDireccion(Address address){
        KieSession kieSession = kContainer.newKieSession();
        Propuesta propuesta = new Propuesta();
        kieSession.insert(address);
        kieSession.insert(propuesta);
        kieSession.fireAllRules();
        // Limpiamos la sesion para evitar perdida de memoria
        kieSession.dispose();
        System.out.println("Propuesta: " + propuesta);
        return propuesta;
    }

}
