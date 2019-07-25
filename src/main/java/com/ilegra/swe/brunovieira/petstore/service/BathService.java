package com.ilegra.swe.brunovieira.petstore.service;

import com.google.inject.Inject;

import java.util.concurrent.atomic.AtomicLong;

public class BathService implements PetStoreService {

    private PetStore petStore;
    private boolean withPerfume;
    private boolean withWater;

    @Inject
    public BathService(PetStore petStore) {
        this.petStore = petStore;
        setDefaultBath();
    }

    public boolean isWithPerfume() {
        return withPerfume;
    }

    public void setWithPerfume(boolean withPerfume) {
        this.withPerfume = withPerfume;
    }

    public boolean isWithWater() {
        return withWater;
    }

    public void setWithWater(boolean withWater) {
        this.withWater = withWater;
    }

    @Override
    public void execute(AtomicLong id) {
        String serviceDetail = "Bath service service, ";
        serviceDetail = withPerfume ? serviceDetail.concat("with perfume, ") : serviceDetail.concat("without perfume, ");
        serviceDetail = withWater ? serviceDetail.concat("with water.") : serviceDetail.concat("dry.");

        petStore.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID"))
                .addServiceToHistory(serviceDetail);

        setDefaultBath();
    }

    public void setDefaultBath() {
        withPerfume = true;
        withWater = true;
    }
}
