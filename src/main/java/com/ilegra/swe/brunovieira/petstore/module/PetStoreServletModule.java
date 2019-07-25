package com.ilegra.swe.brunovieira.petstore.module;

import com.google.inject.servlet.ServletModule;
import com.ilegra.swe.brunovieira.petstore.servlet.PetStoreServlet;

public class PetStoreServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        serve("/pets").with(PetStoreServlet.class);
    }
}
