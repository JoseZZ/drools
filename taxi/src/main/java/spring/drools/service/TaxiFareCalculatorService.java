package spring.drools.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.drools.model.Fare;
import spring.drools.model.TaxiRide;


@Service
public class TaxiFareCalculatorService {

    @Autowired
    private KieContainer kContainer;

    public Long calculateFare(TaxiRide taxiRide, Fare rideFare) {
        // El objeto KieSession se crea usando la instancia de KieContainer
        // El KieSession es el lugar donde se pueden insertar los datos de entrada
        KieSession kieSession = kContainer.newKieSession();
        // Pasamos una variable global al engine de la regla
        kieSession.setGlobal("rideFare", rideFare);
        // Insertamos datos a la regla (facts)
        kieSession.insert(taxiRide);
        // Ejecutamos las reglas
        kieSession.fireAllRules();
        // Limpiamos la sesion para evitar perdida de memoria
        kieSession.dispose();
        // Llamamos al objeto que deberá contener el total calculado
        System.out.println("!! RIDE FARE !! " + rideFare.getTotalFare());
        return rideFare.getTotalFare();
    }
}
