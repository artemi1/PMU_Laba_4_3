package com.example.pmu_laba_4_3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(new VerletMove(this));
        setContentView(R.layout.activity_main);

        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText initX = (EditText)findViewById(R.id.initX);
                float mInitX = Float.parseFloat(initX.getText().toString());

                final EditText initY = (EditText)findViewById(R.id.initY);
                float mInitY = Float.parseFloat(initY.getText().toString());

                final EditText initSpeedX = (EditText)findViewById(R.id.initSpeedX);
                float mInitSpeedX = Float.parseFloat(initSpeedX.getText().toString());

                final EditText initSpeedY = (EditText)findViewById(R.id.initSpeedY);
                float mInitSpeedY = Float.parseFloat(initSpeedY.getText().toString());

                final EditText accelX = (EditText)findViewById(R.id.accelX);
                float mAccelX = Float.parseFloat(accelX.getText().toString());

                final EditText accelY = (EditText)findViewById(R.id.accelY);
                float mAccelY = Float.parseFloat(accelY.getText().toString());

                final EditText deltaT = (EditText)findViewById(R.id.deltaT);
                float mDeltaT = Float.parseFloat(deltaT.getText().toString());

                final EditText angleSpeed = (EditText)findViewById(R.id.angleSpeed);
                float mAngleSpeed = Float.parseFloat(angleSpeed.getText().toString());

                final Arena mArena = (Arena) findViewById(R.id.polygonMove);
                mArena.initValues(mInitX, mInitY, mInitSpeedX, mInitSpeedY,
                        mAccelX, mAccelY, mDeltaT, mAngleSpeed);


            }
        });

        Button buttonStartRandom = (Button) findViewById(R.id.buttonStartRandom);
        buttonStartRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Arena mArena = (Arena) findViewById(R.id.polygonMove);
                mArena.initValuesRandom();
            }
        });
    }
}