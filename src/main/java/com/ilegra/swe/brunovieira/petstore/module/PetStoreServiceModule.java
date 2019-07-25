package com.ilegra.swe.brunovieira.petstore.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.ilegra.swe.brunovieira.petstore.service.BathService;
import com.ilegra.swe.brunovieira.petstore.service.HairCutService;
import com.ilegra.swe.brunovieira.petstore.service.PetStore;
import com.ilegra.swe.brunovieira.petstore.service.PetStoreService;

public class PetStoreServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PetStore.class).in(Scopes.SINGLETON);
        bind(PetStoreService.class).annotatedWith(Names.named("BathService")).to(BathService.class);
        bind(PetStoreService.class).annotatedWith(Names.named("HairCutService")).to(HairCutService.class);
    }
}
