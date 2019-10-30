package com.example.pmu_laba_4_3;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class PolygonMove extends View {

    private boolean goDraw = false;
    private float accelX, accelY, deltaT;
    private Paint paint;
    ArrayList<Vertex> vertices;

    public PolygonMove(Context context, AttributeSet attrs) {
        super(context, attrs);

        vertices = new ArrayList<Vertex>();

        //init paint object
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.rgb(0, 0, 255));

    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (goDraw) {
            float maxX = canvas.getWidth();
            float maxY = canvas.getHeight();

            boolean mInsideBorders = true;

            // все вершины внутри границ канвы?
            for (Vertex mVertex : vertices) {
                if (mVertex.getCurrX() >= maxX || mVertex.getCurrY() >= maxY
                    || mVertex.getCurrX() < 1 || mVertex.getCurrY() <1 ) {
                    mInsideBorders = false;
                }
            }


            // внутри - обрабатываем вершины
            if (mInsideBorders) {
                // отрисовка вершин
                for (Vertex mVertex : vertices) {
                    mVertex.MoveAndDrawVertex(accelX, accelY, deltaT, canvas, paint);
                }

                // отрисовка ребер
                for (int i=0; i<vertices.size()-1; i++){
                    canvas.drawLine(vertices.get(i).getCurrX(), vertices.get(i).getCurrY(),
                            vertices.get(i+1).getCurrX(), vertices.get(i+1).getCurrY(), paint);
                }
                canvas.drawLine(vertices.get(0).getCurrX(), vertices.get(0).getCurrY(),
                        vertices.get(vertices.size()-1).getCurrX(), vertices.get(vertices.size()-1).getCurrY(), paint);


                invalidate();

            // не внутри - заканчиваем обработку
            }else{
                Toast.makeText(getContext(), "Finished!", Toast.LENGTH_SHORT).show();
                vertices.clear();
                goDraw = false;
            }
        }
    }


    // инициализация процесса обработки вершин
    public void initValues(float initX, float initY, float initSpeedX, float initSpeedY,
                            float accelX, float accelY, float deltaT){
        vertices.add (new Vertex(initX-20.0f, initY-20.0f, initSpeedX, initSpeedY, deltaT));
        vertices.add (new Vertex(initX+20.0f, initY-20.0f, initSpeedX, initSpeedY, deltaT));
        vertices.add (new Vertex(initX+20.0f, initY+20.0f, initSpeedX, initSpeedY, deltaT));
        vertices.add (new Vertex(initX-20.0f, initY+20.0f, initSpeedX, initSpeedY, deltaT));

        this.accelX = accelX;
        this.accelY = accelY;
        this.deltaT = deltaT;

        goDraw = true;

        invalidate();
    }
}