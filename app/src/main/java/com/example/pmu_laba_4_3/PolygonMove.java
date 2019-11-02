package com.example.pmu_laba_4_3;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class PolygonMove extends View {

    private boolean goDraw = false;
    //private float accelX, accelY, deltaT;
    //private float angleSpeed;
    //private float centerX, centerY;

    private Paint paint;
    //ArrayList<Vertex> vertices;
    ArrayList<Polygon> polygons;

    public PolygonMove(Context context, AttributeSet attrs) {
        super(context, attrs);

        //vertices = new ArrayList<Vertex>();
        polygons = new ArrayList<Polygon>();

        //init paint object
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.rgb(0, 0, 255));

    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (goDraw) {

            boolean mInsideBorders = true;

            // все ли полигоны внутри границ канвы?
            for (Polygon mPolygon : polygons) {
                if (!mPolygon.IsInsideCanvas(canvas)) {
                    mInsideBorders = false;
                }
            }


            // внутри - обрабатываем вершины
            if (mInsideBorders) {


                // перемещение полигонов
                for (Polygon mPolygon : polygons) {
                    mPolygon.MovePolygon();
                }

                // вращение полигонов
                for (Polygon mPolygon : polygons) {
                    mPolygon.RotatePolygon();
                }

                // отрисовка полигонов
                for (Polygon mPolygon : polygons) {
                    mPolygon.DrawPolygon(canvas, paint);
                }

                invalidate();

            // не внутри - заканчиваем обработку
            }else{
                Toast.makeText(getContext(), "Finished!", Toast.LENGTH_SHORT).show();
                polygons.clear();
                goDraw = false;
            }
        }
    }


    // инициализация процесса обработки полигонов
    public void initValues(float initX, float initY, float initSpeedX, float initSpeedY,
                            float accelX, float accelY, float deltaT, float angleSpeed){
        // заполняем список полигонов
        polygons.add (new Polygon(initX, initY, initSpeedX, initSpeedY, accelX, accelY, deltaT, angleSpeed));

        goDraw = true;

        invalidate();
    }
}