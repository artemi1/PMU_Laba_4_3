package com.example.pmu_laba_4_3;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class Polygon {
    private float prevX, currX, prevY, currY;
    private float accelX, accelY, deltaT;
    private float angleSpeed;

    ArrayList<Vertex> vertices;

    public Polygon(float initX, float initY, float initSpeedX, float initSpeedY,
                   float accelX, float accelY, float deltaT, float angleSpeed){

        vertices = new ArrayList<Vertex>();

        vertices.add (new Vertex(initX-20.0f, initY-20.0f));
        vertices.add (new Vertex(initX+20.0f, initY-20.0f));
        vertices.add (new Vertex(initX+20.0f, initY+20.0f));
        vertices.add (new Vertex(initX-20.0f, initY+20.0f));

        prevX = initX;
        prevY = initY;
        currX = initX + initSpeedX * deltaT;
        currY = initY + initSpeedY * deltaT;

        this.accelX = accelX;
        this.accelY = accelY;
        this.deltaT = deltaT;
        this.angleSpeed = angleSpeed;
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

        for (Vertex mVertex : vertices) {
            mVertex.MoveVertex(deltaX + accelX * deltaT, deltaY + accelY * deltaT);
        }
    }


    // вращение полигона вокруг центральной точки
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
    public void DrawPolygon( Canvas canvas, Paint paint){
        // отрисовка вершин
        for (Vertex mVertex : vertices) {
            canvas.drawCircle(mVertex.GetX(), mVertex.GetY(), 5.0f, paint);
        }

        // отрисовка ребер
        for (int i=0; i<vertices.size()-1; i++){
            canvas.drawLine(vertices.get(i).GetX(), vertices.get(i).GetY(),
                    vertices.get(i+1).GetX(), vertices.get(i+1).GetY(), paint);
        }
        canvas.drawLine(vertices.get(0).GetX(), vertices.get(0).GetY(),
                vertices.get(vertices.size()-1).GetX(), vertices.get(vertices.size()-1).GetY(), paint);
    }


    // все ли вершины полигона лежат внутри границ канвы?
    boolean IsInsideCanvas(Canvas canvas){
        float maxX = canvas.getWidth();
        float maxY = canvas.getHeight();

        float currVertexX, currVertexY;

        // есть ли вершина за границами канвы?
        for (Vertex mVertex : vertices) {
            currVertexX = mVertex.GetX();
            currVertexY = mVertex.GetY();

            if (currVertexX >= maxX || currVertexY >= maxY || currVertexX < 1 || currVertexY <1 ) {
                return false;
            }
        }

        return true;
    }

}


