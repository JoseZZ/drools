package com.training.java.drools.rulebook.beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private String streetAddress;
    private String streetNumber;
    private String zipCode;
    private String city;
    private String region;
    private String country;
    private String channel;
    private String language;
}
