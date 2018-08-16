package com.example.dagger2ex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
