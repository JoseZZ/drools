import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.address.config.AddressConfiguration;
import com.address.model.Address;
import com.address.model.Propuesta;
import com.address.service.AddressValidatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AddressConfiguration.class)
public class ZipCodeTest {

    @Autowired
    AddressValidatorService validatorService;

    @Test
    public void whenZipCodeBelongsToMonacoAndCountryIsMonaco_thenValidationIsOK(){
        Address address = new Address();
        address.setPais("Monaco");
        address.setZipcode("90185");
        Propuesta propuesta = validatorService.valoraDireccion(address);
        assertNotNull(propuesta);
        assertEquals(propuesta.getValoracion(), Long.valueOf(100L));
    }
}
