package com.example.pmu_laba_4_3;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Arena extends View {

    private boolean goDraw = false;
    ArrayList<Polygon> polygons;
    float maxX, maxY;

    public Arena(Context context, AttributeSet attrs) {
        super(context, attrs);

        polygons = new ArrayList<Polygon>();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.maxX = canvas.getWidth();
        this.maxY = canvas.getHeight();

        if (goDraw) {
            // все ли полигоны внутри границ канвы?
            for (int i=0; i<polygons.size(); i++){
                if (!polygons.get(i).IsInsideCanvas(canvas)) {
                    polygons.remove(i);
                }
            }


            // cписок полигонов не пустой?
            if(!polygons.isEmpty()){

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

            // список полигонов пустой - заканчиваем обработку
            }else{
                Toast.makeText(getContext(), "Finished!", Toast.LENGTH_SHORT).show();
                goDraw = false;
            }
        }
    }


    // инициализация процесса обработки одного полигона
    public void initValues(float initX, float initY, float initSpeedX, float initSpeedY,
                            float accelX, float accelY, float deltaT, float angleSpeed){
        // заполняем список полигонов
        polygons.add (new Polygon(initX, initY, initSpeedX, initSpeedY, accelX, accelY, deltaT, angleSpeed));

        goDraw = true;
        invalidate();
    }

    // инициализация процесса обработки случайных полигонов
    public void initValuesRandom(){
        long millisecondsFromEpoch = System.currentTimeMillis();
        Random random = new Random(millisecondsFromEpoch);

        for (int i=0; i<random.nextInt(9)+1; i++){
            polygons.add (new Polygon(random.nextFloat()*maxX, random.nextFloat()*maxY,
                    random.nextFloat()*5.0f-2.5f, random.nextFloat()*5.0f-2.5f,
                    random.nextFloat()*5.0f-2.5f, random.nextFloat()*5.0f-2.5f,
                    random.nextFloat()*3.0f, random.nextFloat()*4.0f));
        }

        goDraw = true;
        invalidate();

    }
}