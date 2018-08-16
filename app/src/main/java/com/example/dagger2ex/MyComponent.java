package com.example.dagger2ex;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {MyModule.class})
interface MyComponent {
    Vehicle provideVehicle();

    void inject(MainActivity main);
}
