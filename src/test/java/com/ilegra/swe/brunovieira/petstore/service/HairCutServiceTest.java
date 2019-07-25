package com.ilegra.swe.brunovieira.petstore.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ilegra.swe.brunovieira.petstore.domain.Pet;
import com.ilegra.swe.brunovieira.petstore.module.PetStoreServiceModule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HairCutServiceTest {

    HairCutService hairCutService;
    Pet pet;

    @Before
    public void setup() {
        Injector injector = Guice.createInjector(new PetStoreServiceModule());
        PetStore petStore = injector.getInstance(PetStore.class);

        pet = new Pet("Dog", "Pit Bull", 5);
        petStore.addPet(pet);

        hairCutService = injector.getInstance(HairCutService.class);
    }

    @Test
    public void shouldCutHairShort() {
        hairCutService.setLength(Length.SHORT);
        hairCutService.execute(pet.getId());

        assertEquals(1, pet.getServices().size());
        assertTrue(pet.getServices().get(0).contains("length SHORT"));
    }

    @Test
    public void shouldCutHairLong() {
        hairCutService.setLength(Length.LONG);
        hairCutService.execute(pet.getId());

        assertEquals(1, pet.getServices().size());
        assertTrue(pet.getServices().get(0).contains("length LONG"));
    }

    @Test
    public void shouldBeShortHairCutDefault() {
        hairCutService.execute(pet.getId());

        assertEquals(1, pet.getServices().size());
        assertTrue(pet.getServices().get(0).contains("length SHORT"));
    }

    @Test
    public void shouldChangeLengthToShortAfterToDoService() {
        hairCutService.setLength(Length.LONG);
        hairCutService.execute(pet.getId());

        assertEquals(Length.SHORT, hairCutService.getLength());
    }

}
