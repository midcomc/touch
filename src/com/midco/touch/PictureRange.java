package com.midco.touch;

/**
 * Created by Administrator on 2015/4/22.
 */
public class PictureRange {
    float x1,x2,y1,y2 = 0;
    public PictureRange(float x1,float x2,float y1,float y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public float getX1(){
        return x1;
    }
    public float getX2(){
        return x2;
    }
    public float getY1(){
        return y1;
    }
    public float getY2(){
        return y2;
    }

}
