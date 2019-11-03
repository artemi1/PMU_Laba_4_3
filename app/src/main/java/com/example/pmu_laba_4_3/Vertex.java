package com.example.pmu_laba_4_3;


public class Vertex {
    private float X, Y;


    public Vertex (float initX, float initY){
        X = initX;
        Y = initY;
    }

    public float GetX() {return X;}
    public float GetY() {return Y;}


    public void RotateVertex(float newBasisVectX_X, float newBasisVectX_Y,
                             float newBasisVectY_X, float newBasisVectY_Y) {
        this.X = this.X * newBasisVectX_X + this.Y * newBasisVectY_X;
        this.Y = this.X * newBasisVectX_Y + this.Y * newBasisVectY_Y;
    }

}
