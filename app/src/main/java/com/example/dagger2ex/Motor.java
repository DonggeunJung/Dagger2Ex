package com.example.dagger2ex;

class Motor {
    private int rpm;

    Motor() {
        this.rpm = 0;
    }

    int getRpm() {
        return rpm;
    }

    void accelerate(int value) {
        rpm += value;
    }

    void decelerate(int value) {
        rpm -= value;
    }

    void brake() {
        rpm = 0;
    }
}
