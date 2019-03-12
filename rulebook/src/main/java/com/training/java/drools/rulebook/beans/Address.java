package com.training.java.drools.rulebook.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
