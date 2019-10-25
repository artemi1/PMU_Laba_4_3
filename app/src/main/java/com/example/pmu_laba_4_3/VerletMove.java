package com.example.pmu_laba_4_3;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import java.util.*;

public class VerletMove extends View {
    private final float initX=0.0f, initY=0.0f;
    private final float accelX=1.0f, accelY=2.0f;
    private final float initSpeedX=1.0f, initSpeedY=1.0f;
    private final float deltaT=0.1f;

    private float prevX, currX, prevY, currY;

    private Paint paint;

    public VerletMove(Context context) {
        super(context);

        initValues();

        //init paint object
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.rgb(0, 0, 255));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        float maxX = canvas.getWidth();
        float maxY = canvas.getHeight();

        if (currX < maxX && currY < maxY) {

            canvas.drawCircle(currX, currY, 30, paint);

            float deltaX = currX - prevX;
            float deltaY = currY - prevY;

            float nextX = currX + deltaX + accelX * deltaT;
            float nextY = currY + deltaY + accelY * deltaT;

            prevX = currX;
            prevY = currY;

            currX = nextX;
            currY = nextY;

            invalidate();
        }else{
            initValues();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN ) { //run animation
            invalidate();
            return true;
        }
        return false;
    }

    private void initValues(){
        prevX = initX;
        prevY = initY;
        currX = initX + initSpeedX * deltaT;
        currY = initY + initSpeedY * deltaT;
    }
}