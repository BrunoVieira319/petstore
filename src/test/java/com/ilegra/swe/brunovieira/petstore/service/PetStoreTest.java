package com.ilegra.swe.brunovieira.petstore.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ilegra.swe.brunovieira.petstore.domain.Pet;
import com.ilegra.swe.brunovieira.petstore.module.PetStoreServiceModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class PetStoreTest {

    PetStore petStore;
    Pet pet;
    BathService bathService;

    public PetStoreTest() {
        Injector injector = Guice.createInjector(new PetStoreServiceModule());
        petStore = injector.getInstance(PetStore.class);
        bathService = injector.getInstance(BathService.class);
    }

    @Before
    public void setup() {
        pet = new Pet("Dog", "Pit Bull", 5);
    }

    @After
    public void deleteAll() {
        petStore.deleteAll();
    }

    @Test
    public void shouldAddPetToPetStore() {
        petStore.addPet(pet);
        assertTrue(petStore.findById(pet.getId()).isPresent());
    }

    @Test
    public void shouldFindPetById() {
        petStore.addPet(pet);
        Optional<Pet> optionalPet = petStore.findById(pet.getId());
        assertTrue(optionalPet.isPresent());
    }

    @Test
    public void shouldFindPetsByAge() {
        petStore.addPet(pet);
        petStore.addPet(new Pet("Dog1", "any", 5));
        petStore.addPet(new Pet("Dog2", "any", 5));
        List<Pet> petsByAge = petStore.findPetByAge(5);

        assertEquals(3, petsByAge.size());
    }

    @Test
    public void shouldFindPetsWithMoreRevenue() {
        petStore.addPet(pet);

        Pet pluto = new Pet("Pluto", "race", 10);
        petStore.addPet(pluto);
        bathService.execute(pluto.getId());

        List<Pet> top1MoreRevenue = petStore.findPetsWithMoreRevenue(1);

        assertEquals(1, top1MoreRevenue.size());
        assertEquals(pluto, top1MoreRevenue.get(0));
    }

    @Test
    public void shouldDeletePetById() {
        petStore.addPet(pet);
        petStore.deletePetById(pet.getId());

        Optional<Pet> deletedPet = petStore.findById(pet.getId());
        assertFalse(deletedPet.isPresent());
    }
}
