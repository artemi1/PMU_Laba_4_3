package com.example.pmu_laba_4_3;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Arena extends View {

    private boolean goDraw = false;
    ArrayList<Polygon> polygons;

    public Arena(Context context, AttributeSet attrs) {
        super(context, attrs);

        polygons = new ArrayList<Polygon>();

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
                    mPolygon.DrawPolygon(canvas);
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