package com.example.pmu_laba_4_3;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Vertex {
    private float prevX, currX, prevY, currY;


    public Vertex (float initX, float initY, float initSpeedX, float initSpeedY, float deltaT){
        prevX = initX;
        prevY = initY;

        currX = initX + initSpeedX * deltaT;
        currY = initY + initSpeedY * deltaT;
    }

    // двигаем вершину по методу Верле и рисуем ее
    public void MoveAndDrawVertex (float accelX, float accelY, float deltaT, Canvas canvas, Paint paint){
        float deltaX = currX - prevX;
        float deltaY = currY - prevY;

        float nextX = currX + deltaX + accelX * deltaT;
        float nextY = currY + deltaY + accelY * deltaT;

        prevX = currX;
        prevY = currY;

        currX = nextX;
        currY = nextY;

        canvas.drawCircle(currX, currY, 5.0f, paint);
    }

    public float getCurrX() {return currX;}
    public float getCurrY() {return currY;}


}
