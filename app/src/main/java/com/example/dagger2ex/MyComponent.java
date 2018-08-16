package com.example.dagger2ex;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hani on 5/29/17.
 */
@Singleton
@Component(modules = {MyModule.class})
interface MyComponent {
    Vehicle provideVehicle();

    void inject(MainActivity main);
}