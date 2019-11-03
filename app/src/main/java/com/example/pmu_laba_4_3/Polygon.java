package com.example.pmu_laba_4_3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

public class Polygon {
    private float prevX, currX, prevY, currY;
    private float accelX, accelY, deltaT;
    private float angleSpeed;

    ArrayList<Vertex> vertices;

    private Paint paint;

    public Polygon(float initX, float initY, float initSpeedX, float initSpeedY,
                   float accelX, float accelY, float deltaT, float angleSpeed){

        long millisecondsFromEpoch = System.currentTimeMillis();
        Random random = new Random(millisecondsFromEpoch);

        vertices = new ArrayList<Vertex>();

        vertices.add (new Vertex(-30.0f, -30.0f));
        vertices.add (new Vertex(30.0f, -30.0f));
        vertices.add (new Vertex(30.0f, 30.0f));
        vertices.add (new Vertex(-30.0f, 30.0f));

        this.prevX = initX;
        this.prevY = initY;
        this.currX = initX + initSpeedX * deltaT;
        this.currY = initY + initSpeedY * deltaT;

        this.accelX = accelX;
        this.accelY = accelY;
        this.deltaT = deltaT;
        this.angleSpeed = angleSpeed;

        //init paint object
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
    }

    // движкение полигона по методу Верле
    public void MovePolygon (){
        float deltaX = currX - prevX;
        float deltaY = currY - prevY;

        float nextX = currX + deltaX + accelX * deltaT;
        float nextY = currY + deltaY + accelY * deltaT;

        prevX = currX;
        prevY = currY;
        currX = nextX;
        currY = nextY;
    }


    // вращение полигона вокруг центральной точки
    // путем поворота базисных векторов
    public void RotatePolygon (){
        float newBasisVectorX_X, newBasisVectorX_Y;
        float newBasisVectorY_X, newbasisVectorY_Y;

        // оригинальный базисный вектор X=(1,0)
        newBasisVectorX_X = (float)Math.cos(Math.toRadians(angleSpeed));
        newBasisVectorX_Y = (float)-Math.sin(Math.toRadians(angleSpeed));

        // оригинальный базисный вектор Y=(0,1)
        newBasisVectorY_X = (float)Math.sin(Math.toRadians(angleSpeed));
        newbasisVectorY_Y = (float)Math.cos(Math.toRadians(angleSpeed));

        for (Vertex mVertex : vertices) {
            mVertex.RotateVertex(newBasisVectorX_X, newBasisVectorX_Y,
                    newBasisVectorY_X, newbasisVectorY_Y);
        }
    }



    // отрисовка полигона
    public void DrawPolygon( Canvas canvas){
        // отрисовка вершин
        for (Vertex mVertex : vertices) {
            canvas.drawCircle(this.currX+mVertex.GetX(), this.currY+mVertex.GetY(), 5.0f, paint);
        }

        // отрисовка ребер
        for (int i=0; i<vertices.size()-1; i++){
            canvas.drawLine(this.currX+vertices.get(i).GetX(), this.currY+vertices.get(i).GetY(),
                    this.currX+vertices.get(i+1).GetX(), this.currY+vertices.get(i+1).GetY(), paint);
        }
        canvas.drawLine(this.currX+vertices.get(0).GetX(), this.currY+vertices.get(0).GetY(),
                this.currX+vertices.get(vertices.size()-1).GetX(), this.currY+vertices.get(vertices.size()-1).GetY(), paint);
    }


    // все ли вершины полигона лежат внутри границ канвы?
    boolean IsInsideCanvas(Canvas canvas){
        float maxX = canvas.getWidth();
        float maxY = canvas.getHeight();

        float currVertexX, currVertexY;

        // есть ли вершина за границами канвы?
        for (Vertex mVertex : vertices) {
            currVertexX = this.currX+mVertex.GetX();
            currVertexY = this.currY+mVertex.GetY();

            if (currVertexX >= maxX || currVertexY >= maxY || currVertexX < 1 || currVertexY <1 ) {
                return false;
            }
        }

        return true;
    }

}


