package com.ilegra.swe.brunovieira.petstore.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PetTest {

    @Test
    public void shouldCreatePet() {
        Pet pet = new Pet("Dog", "Pit bull", 5);

        assertEquals("Dog", pet.getName());
        assertEquals("Pit bull", pet.getRace());
        assertEquals(5, pet.getAge());
        assertEquals(0, pet.getServices().size());
        assertNotNull(pet.getId());
    }

    @Test
    public void shouldAddServiceToPet() {
        Pet pet = new Pet("Dog", "Pit bull", 5);
        pet.addServiceToHistory("One Service");

        assertEquals(1, pet.getServices().size());
        assertEquals("One Service", pet.getServices().get(0));
    }
}
