package org.spring.drools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.drools.service.TaxiFareConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TaxiFareConfiguration.class)
public class SpringContextIntegrationTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
