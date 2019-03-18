package com.training.java.drools.droolstemplates.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private String zipCode;
    private String city;
    private String region;
    private String country;
}
