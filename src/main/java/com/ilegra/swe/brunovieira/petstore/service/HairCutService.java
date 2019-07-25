package com.ilegra.swe.brunovieira.petstore.service;

import com.google.inject.Inject;

import java.util.concurrent.atomic.AtomicLong;

public class HairCutService implements PetStoreService {

    private PetStore petStore;
    private Length length;

    @Inject
    public HairCutService(PetStore petStore) {
        this.petStore = petStore;
        this.length = Length.SHORT;
    }

    public void setLength(Length length) {
        this.length = length;
    }

    public Length getLength() {
        return length;
    }

    @Override
    public void execute(AtomicLong id) {
        petStore.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID"))
                .addServiceToHistory("Hair cut service with length " + length);

        length = Length.SHORT;
    }
}
