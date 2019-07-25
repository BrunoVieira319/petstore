package com.ilegra.swe.brunovieira.petstore.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ilegra.swe.brunovieira.petstore.domain.Pet;
import com.ilegra.swe.brunovieira.petstore.module.PetStoreServiceModule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BathServiceTest {

    BathService bathService;
    Pet pet;

    @Before
    public void setup() {
        Injector injector = Guice.createInjector(new PetStoreServiceModule());
        PetStore petStore = injector.getInstance(PetStore.class);

        pet = new Pet("Dog", "Pit Bull", 5);
        petStore.addPet(pet);

        bathService = injector.getInstance(BathService.class);
        bathService.setDefaultBath();
    }

    @Test
    public void shouldDoDefaultBath() {
        bathService.execute(pet.getId());

        assertEquals(1, pet.getServices().size());
        assertTrue(pet.getServices().get(0).contains("with perfume, with water"));
    }

    @Test
    public void shouldDoBathWithoutPerfume() {
        bathService.setWithPerfume(false);
        bathService.execute(pet.getId());

        assertEquals(1, pet.getServices().size());
        assertTrue(pet.getServices().get(0).contains("without perfume, with water"));
    }

    @Test
    public void shouldDoDryBath() {
        bathService.setWithWater(false);
        bathService.execute(pet.getId());

        assertEquals(1, pet.getServices().size());
        assertTrue(pet.getServices().get(0).contains("with perfume, dry"));
    }

    @Test
    public void shouldChangeToDefaultBathAfterExecuteService() {
        bathService.setWithWater(false);
        bathService.setWithPerfume(false);
        bathService.execute(pet.getId());

        assertTrue(bathService.isWithPerfume());
        assertTrue(bathService.isWithWater());
    }

}
