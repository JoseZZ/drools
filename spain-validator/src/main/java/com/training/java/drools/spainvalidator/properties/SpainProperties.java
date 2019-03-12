package com.training.java.drools.spainvalidator.properties;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

//@PropertySource("classpath:spain.properties")
@ConfigurationProperties("spain")
@EnableConfigurationProperties
public class SpainProperties {

    private Map<String, String> province = new HashMap<>();

    public Map<String, String> getProvince() {
        return this.province;
    }

    public void setProvince(Map<String, String> spain){
        this.province = spain;
    }
}
