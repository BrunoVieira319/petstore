package com.ilegra.swe.brunovieira.petstore.servlet;

import com.ilegra.swe.brunovieira.petstore.domain.Pet;
import com.ilegra.swe.brunovieira.petstore.service.PetStore;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Singleton
public class PetStoreServlet extends HttpServlet {

    private PetStore petStore;

    @Inject
    public PetStoreServlet(PetStore petStore) {
        this.petStore = petStore;
        petStore.addPet(new Pet("Scooby-Doo", "Great Dane", 50));
        petStore.addPet(new Pet("Pluto", "Bloodhound", 90));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Pet> pets = petStore.findPetsWithMoreRevenue(2);
        pets.forEach(pet -> {
            try {
                resp.getWriter().println(pet.getName() + ", " + pet.getRace());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
