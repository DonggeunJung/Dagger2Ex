package com.example.dagger2ex;

import javax.inject.Inject;

class Vehicle {
    private Motor motor;

    @Inject
    Vehicle(Motor motor) {
        this.motor = motor;
    }

    void increaseSpeed(int value) {
        motor.accelerate(value);
    }

    void decreaseSpeed(int value) {
        motor.decelerate(value);
    }

    void stop() {
        motor.brake();
    }

    int getSpeed() {
        return motor.getRpm();
    }
}
