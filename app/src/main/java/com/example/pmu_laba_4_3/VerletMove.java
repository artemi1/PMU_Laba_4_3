package com.example.pmu_laba_4_3;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

public class VerletMove extends View {

    //private final String prompt = "Введите значения и нажмите кнопку Start";
    private boolean goDraw = false;
    private float prevX, currX, prevY, currY, accelX, accelY, deltaT;
    private Paint paint;

    public VerletMove(Context context, AttributeSet attrs) {
        super(context, attrs);

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

        if (goDraw && currX < maxX && currY < maxY) {

            canvas.drawRect(currX - 20.0f, currY - 20.0f, currX + 20.0f, currY + 20.0f, paint);

            float deltaX = currX - prevX;
            float deltaY = currY - prevY;

            float nextX = currX + deltaX + accelX * deltaT;
            float nextY = currY + deltaY + accelY * deltaT;

            prevX = currX;
            prevY = currY;

            currX = nextX;
            currY = nextY;

            invalidate();
        }
    }


    public void initValues(float initX, float initY, float initSpeedX, float initSpeedY,
                            float accelX, float accelY, float deltaT){
        prevX = initX;
        prevY = initY;
        currX = initX + initSpeedX * deltaT;
        currY = initY + initSpeedY * deltaT;

        this.accelX = accelX;
        this.accelY = accelY;
        this.deltaT = deltaT;

        goDraw = true;

        invalidate();
    }
}