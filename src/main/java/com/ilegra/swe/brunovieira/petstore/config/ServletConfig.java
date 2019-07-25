package com.ilegra.swe.brunovieira.petstore.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.ilegra.swe.brunovieira.petstore.module.PetStoreServiceModule;
import com.ilegra.swe.brunovieira.petstore.module.PetStoreServletModule;

public class ServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new PetStoreServiceModule(), new PetStoreServletModule());
    }
}
