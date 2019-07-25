package com.ilegra.swe.brunovieira.petstore.service;

import com.ilegra.swe.brunovieira.petstore.domain.Pet;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

public class PetStore {

    private Map<AtomicLong, Pet> pets;

    public PetStore() {
        this.pets = new HashMap<>();
    }

    public void addPet(Pet pet) {
        pets.put(pet.getId(), pet);
    }

    public Optional<Pet> findById(AtomicLong id) {
        try {
            return Optional.of(pets.get(id));
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    public List<Pet> findPetByAge(int age) {
        return pets.values()
                .stream()
                .filter(p -> p.getAge() == age)
                .collect(Collectors.toList());
    }

    public List<Pet> findPetsWithMoreRevenue(int top) {
        List<Pet> pets = this.pets.values()
                .stream()
                .sorted(comparingInt(pet -> pet.getServices().size()))
                .collect(Collectors.toList());

        Collections.reverse(pets);
        return pets.subList(0, top);
    }

    public void deleteAll() {
        pets = new HashMap<>();
    }

    public void deletePetById(AtomicLong id) {
        pets.remove(id);
    }
}
