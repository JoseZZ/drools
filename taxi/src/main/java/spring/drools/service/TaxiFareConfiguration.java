package spring.drools.service;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("spring.drools.service")
public class TaxiFareConfiguration {

    // Esta clase se encargara de instanciar el TaxiFareCalculatorService y sus dependencias

    public static final String drlFile = "TAXI_FARE_RULE.drl";

    @Bean
    public KieContainer kieContainer() {
        // KieService es un singleton que actua como punto de entrada para obtener todos los
        // servicios que Kie provee. Se obtiene la instancia mediante una factoria
        KieServices kieServices = KieServices.Factory.get();

        // Obtenemos el container que contendrá los objetos que necesitamos para ejecutar
        // nuestro motor de reglas. Se construye con la ayuda de otros beans como:
        // KieFileSystem, KieBuilder y KieModule
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        // El KieModule es un container para todos los recursos que se necesitan para definir
        // el KieBase, que es un repositoria que contiene el conocimiento relacionado con la aplicacion,
        // como las reglas, procesos, funciones, modelado de tipos, etc, y se guarda en el KieModule
        // El KieBase se puede obtener del KieContainer
        KieModule kieModule = kieBuilder.getKieModule();

        // El KieContainer contiene el KieModule donde se ha definido el KieBase
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
}
