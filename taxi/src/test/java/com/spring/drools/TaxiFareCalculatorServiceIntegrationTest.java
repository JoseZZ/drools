package com.spring.drools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.drools.model.Fare;
import spring.drools.model.TaxiRide;
import spring.drools.service.TaxiFareCalculatorService;
import spring.drools.service.TaxiFareConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TaxiFareConfiguration.class)
public class TaxiFareCalculatorServiceIntegrationTest {

    @Autowired
    private TaxiFareCalculatorService taxiFareCalculatorService;

    @Test
    public void whenNightSurchargeFalseAndDistanceLessThan10_thenFixFareWithoutNightSurcharge() {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(false);
        taxiRide.setDistanceInMile(9L);
        Fare rideFare = new Fare();
        Long totalCharge = taxiFareCalculatorService.calculateFare(taxiRide, rideFare);

        assertNotNull(totalCharge);
        assertEquals(Long.valueOf(70), totalCharge);
    }
    
    @Test
    public void whenNightSurchargeTrueAndDistanceLessThan10_thenFixFareWithNightSurcharge() {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(true);
        taxiRide.setDistanceInMile(5L);
        Fare rideFare = new Fare();
        Long totalCharge = taxiFareCalculatorService.calculateFare(taxiRide, rideFare);

        assertNotNull(totalCharge);
        assertEquals(Long.valueOf(100), totalCharge);
    }

    @Test
    public void whenNightSurchargeFalseAndDistanceLessThan100_thenDoubleFareWithoutNightSurcharge() {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(false);
        taxiRide.setDistanceInMile(50L);
        Fare rideFare = new Fare();
        Long totalCharge = taxiFareCalculatorService.calculateFare(taxiRide, rideFare);

        assertNotNull(totalCharge);
        assertEquals(Long.valueOf(170), totalCharge);
    }
    
    @Test
    public void whenNightSurchargeTrueAndDistanceLessThan100_thenDoubleFareWithNightSurcharge() {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(true);
        taxiRide.setDistanceInMile(50L);
        Fare rideFare = new Fare();
        Long totalCharge = taxiFareCalculatorService.calculateFare(taxiRide, rideFare);

        assertNotNull(totalCharge);
        assertEquals(Long.valueOf(250), totalCharge);
    }
    
    @Test
    public void whenNightSurchargeFalseAndDistanceGreaterThan100_thenExtraPercentFareWithoutNightSurcharge() {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(false);
        taxiRide.setDistanceInMile(100L);
        Fare rideFare = new Fare();
        Long totalCharge = taxiFareCalculatorService.calculateFare(taxiRide, rideFare);

        assertNotNull(totalCharge);
        assertEquals(Long.valueOf(270), totalCharge);
    }
    
    @Test
    public void whenNightSurchargeTrueAndDistanceGreaterThan100_thenExtraPercentFareWithNightSurcharge() {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(true);
        taxiRide.setDistanceInMile(100L);
        Fare rideFare = new Fare();
        Long totalCharge = taxiFareCalculatorService.calculateFare(taxiRide, rideFare);

        assertNotNull(totalCharge);
        assertEquals(Long.valueOf(400), totalCharge);
    }

}
