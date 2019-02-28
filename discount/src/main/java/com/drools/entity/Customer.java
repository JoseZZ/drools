package com.drools.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    public static final int DEFAULT_CUSTOMER = 0;
    public static final int SILVER_CUSTOMER = 1;
    public static final int GOLD_CUSTOMER = 2;

    private int status;
    private String name;

}
