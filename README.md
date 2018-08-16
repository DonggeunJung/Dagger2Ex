# Dagger2Ex

[Android & Java] Dagger2 - Dependency Injection library sample source project


[Image1 : Speed - 0]
[Image2 : Increase Speed]

<div>
<img src="https://github.com/DonggeunJung/Dagger2Ex/blob/master/Dagger2Ex_Capture01.png?raw=true width="400px"></img>
</div>
<div>
<img src="https://github.com/DonggeunJung/Dagger2Ex/blob/master/Dagger2Ex_Capture02.png?raw=true width="400px"></img>
</div>


< Used Android API >
1. Dagger2


< Making source project sequence >

# Add library
 = build.gradle (Module : app)

dependencies {
    ~
    //Dagger Dependency Injector
    compile "com.google.dagger:dagger:2.11"
    compile "com.google.dagger:dagger-android:2.11"
    compile "com.google.dagger:dagger-android-support:2.11"
    annotationProcessor "com.google.dagger:dagger-compiler:2.11"
}

= build.gradle (Project: <Project name>)

provided 'javax.annotation:jsr250-api:1.0'


# Source code

 = Motor.java - create

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


 = Vehicle.java - create

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


 = MyModule.java - create

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
class MyModule {

    @Provides
    @Singleton
    Motor provideMotor() {
        return new Motor();
    }

    @Provides
    @Singleton
    Vehicle provideVehicle() {
        return new Vehicle(new Motor());
    }
}


 = MyComponent.java - create

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {MyModule.class})
interface MyComponent {
    Vehicle provideVehicle();

    void inject(MainActivity main);
}


 = activity_main.xml - modify
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft ="24dp"
        android:layout_marginTop="21dp"
        android:text="Hello World!" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignLeft="@+id/textView1"
        android:layout_marginTop="82dp"
        android:onClick="onBtnAccelerate"
        android:text="Accelerate" />

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentEnd="true"
        android:layout_alignParentRIght="true"
        android:layout_alignTop="@+id/button"
        android:layout_marginEnd="22dp"
        android:onClick="onBtnDecelerate"
        android:text="Decelerate" />

    <Button
        android:id="@+id/button3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignRight="@+id/button2"
        android:layout_alignParentTop="true"
        android:layout_marginTop="14dp"
        android:onClick="onBtnStop"
        android:text="Stop" />

</RelativeLayout>


 = MainActivity.java - modify
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Vehicle vehicle;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView1);

        ///*** If you find error at 'DaggerMyComponent',  ***///
        // 1. Set comment below code / 2. Build again / 3. Clear comment mark / 4. Build & run
        MyComponent component = DaggerMyComponent.builder().build();
        component.inject(this);
        ///***  ***///

        showSpeed();
    }

    public void showSpeed() {
        int speed = vehicle.getSpeed();
        String strSpeed = Integer.toString(speed);
        textView.setText(strSpeed);
    }

    public void onBtnAccelerate(View v) {
        vehicle.increaseSpeed(2);
        showSpeed();
    }

    public void onBtnDecelerate(View v) {
        vehicle.decreaseSpeed(2);
        showSpeed();
    }

    public void onBtnStop(View v) {
        vehicle.stop();
        showSpeed();
    }

}


< Additional Infomation >

GitHub Link : https://github.com/DonggeunJung/Dagger2Ex

Download APK file : none

Any question => Email : topsan72@gmail.com / Author : Donggeun Jung (Dennis)
