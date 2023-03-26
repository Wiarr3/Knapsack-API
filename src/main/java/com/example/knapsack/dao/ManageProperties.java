package com.example.knapsack.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Component
public class ManageProperties {
    @Autowired
    private Environment environment;
    private final Properties properties;

    public ManageProperties(Properties properties) {
        this.properties = properties;
    }

    public Integer setMaxWeight(Integer weight) {
        MutablePropertySources propertySources = ((AbstractEnvironment) environment).getPropertySources();
        properties.setProperty("knacksackMaxWeight", weight.toString());
        propertySources.addFirst(new PropertiesPropertySource("myProperties", properties));
        System.out.println(environment.getProperty("knacksackMaxWeight"));
        return weight;
    }

    public Integer setLength(Integer length) {
        MutablePropertySources propertySources = ((AbstractEnvironment) environment).getPropertySources();
        properties.setProperty("knacksackAmountOfItems", length.toString());
        propertySources.addFirst(new PropertiesPropertySource("myProperties", properties));
        System.out.println(environment.getProperty("knacksackAmountOfItems"));
        return length;
    }

}
