package com.ilegra.swe.brunovieira.petstore.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Pet {

    private AtomicLong id;
    private String name;
    private String race;
    private int age;
    private List<String> historyServices;

    public Pet(String name, String race, int age) {
        this.id = new AtomicLong();
        this.name = name;
        this.race = race;
        this.age = age;
        this.historyServices = new ArrayList<>();
    }

    public void addServiceToHistory(String service) {
        historyServices.add(service);
    }

    public AtomicLong getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public int getAge() {
        return age;
    }

    public List<String> getServices() {
        return Collections.unmodifiableList(historyServices);
    }
}
