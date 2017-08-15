package com.github.naoghuman.lib.testdata.internal;

import java.util.List;
import java.util.Random;

import javafx.collections.FXCollections;

public final class ConfigurationHelper {
    
    public static final Random RANDOM = new Random();
    
    private static final List<Integer> QUANTITY_ENTITIES = FXCollections.observableArrayList();
    private static final List<Integer> QUANTITY_TIMEPERIOD = FXCollections.observableArrayList();
    
    private static ConfigurationHelper instance = null;
    
    public static ConfigurationHelper getDefault() {
        if (instance == null) {
            instance = new ConfigurationHelper();
        }
        
        return instance;
    }
    
    private ConfigurationHelper() { 
        this.initialize();
    }
    
    private void initialize() {
        this.initializeQuantityEntities();
        this.initializeQuantityTimePeriod();
    }

    private void initializeQuantityEntities() {
        QUANTITY_ENTITIES.add(100);
        QUANTITY_ENTITIES.add(250);
        QUANTITY_ENTITIES.add(500);
        QUANTITY_ENTITIES.add(1000);
        QUANTITY_ENTITIES.add(2500);
        QUANTITY_ENTITIES.add(5000);
        QUANTITY_ENTITIES.add(10000);
        QUANTITY_ENTITIES.add(12500);
        QUANTITY_ENTITIES.add(15000);
        QUANTITY_ENTITIES.add(20000);
        QUANTITY_ENTITIES.add(25000);
        QUANTITY_ENTITIES.add(30000);
        QUANTITY_ENTITIES.add(35000);
        QUANTITY_ENTITIES.add(40000);
        QUANTITY_ENTITIES.add(50000);
        QUANTITY_ENTITIES.add(75000);
        QUANTITY_ENTITIES.add(100000);
    }

    private void initializeQuantityTimePeriod() {
        QUANTITY_TIMEPERIOD.add(1);
        QUANTITY_TIMEPERIOD.add(2);
        QUANTITY_TIMEPERIOD.add(3);
        QUANTITY_TIMEPERIOD.add(4);
        QUANTITY_TIMEPERIOD.add(5);
        QUANTITY_TIMEPERIOD.add(10);
        QUANTITY_TIMEPERIOD.add(15);
        QUANTITY_TIMEPERIOD.add(20);
        QUANTITY_TIMEPERIOD.add(25);
        QUANTITY_TIMEPERIOD.add(50);
        QUANTITY_TIMEPERIOD.add(75);
    }
    
    public List<Integer> getQuantityEntities() {
        return QUANTITY_ENTITIES;
    }
    
    public List<Integer> getQuantityTimePeriods() {
        return QUANTITY_TIMEPERIOD;
    }
    
}
