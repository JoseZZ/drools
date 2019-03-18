package com.training.java.drools.droolstemplates;

import com.training.java.drools.droolstemplates.model.Address;
import com.training.java.drools.droolstemplates.model.Propuesta;
import com.training.java.drools.droolstemplates.model.SpainZipValidation;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DroolsTemplatesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DroolsTemplatesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SpainZipValidation spainZipValidation = new SpainZipValidation();
        Address address = Address.builder().country("ES").region("M").zipCode("28080").build();
        String drlFile = createDRL(spainZipValidation);
        KieSession kieSession = createKieSessionFromDRL(drlFile);
        Propuesta propuesta = new Propuesta();
        kieSession.insert(address);
        kieSession.setGlobal("propuesta", propuesta);
        kieSession.fireAllRules();
        // Limpiamos la sesion para evitar perdida de memoria
        kieSession.dispose();
        System.out.println("Propuesta: " + propuesta);
    }

    public String createDRL(SpainZipValidation zipValidation) throws Exception {

        log.debug("Se crea la regla drl:{} ", zipValidation);
        String drl = null;
        try {
            InputStream template = DroolsTemplatesApplication.class
                .getResourceAsStream("/rules/templates/Validation.drt");
            List<SpainZipValidation> data = new ArrayList<>();
            data.add(zipValidation);
            ObjectDataCompiler converter = new ObjectDataCompiler();
            drl = converter.compile(data, template);
            template.close();
            log.debug("Se ha creado correctamente el archivo drl");
        } catch (Exception e) {
            throw new Exception("Excepcion al crear el archivo drl");
        }
        return drl;
    }

    // Crea una KieSession de una cadena conteniendo el codigo DRL
    private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.WARNING, Message.Level.
            ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }
            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        return kieHelper.build().newKieSession();
    }

    }
