package com.midco.touch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by MIDCO on 2015/4/13.
 */
public class GameTools {

    private static float DisplayW = 0;//遊戲屏幕寬度
    private static float DisplayH = 0;//遊戲屏幕高度
    private static int refurbishTime =100;//遊戲屏幕刷新時間間隔

    //設置屏幕大小
    public static void setDisplayWH(float DisW, float DisH) {
        DisplayW = DisW;
        DisplayH = DisH;
    }
    //獲取屏幕寬
    public  static float getDisplayW(){
        return DisplayW;
    }
    //獲取屏幕高
    public static float getDisplayH(){
        return DisplayH;
    }

    //獲取遊戲屏幕刷新時間間隔
    public static int getRefurbishTime(){
        return refurbishTime;
    }

    public static void setRefurbishTime(int time){
        refurbishTime = time;
    }
    /**
     * ******************************************************劃字符串*****************************************************************************************
     */

     /*居中畫出字符串 */
    public static void drawString(Canvas canvas, String str, Paint paint) {
        Rect bounds = new Rect();
        paint.getTextBounds(str, 0, str.length(), bounds);
        float x1 = 0;
        if((DisplayW / 2)>=(bounds.width() / 2)){
            x1=(DisplayW / 2) - ( bounds.width() / 2);
        }

        drawString(canvas, str, DisplayW,x1 ,DisplayH / 2, paint.getTextSize(), paint.getColor(), paint);
    }

    /*在指定地方橫向居中畫出字符串 */
    public static float[] drawString(Canvas canvas, String str, float y, Paint paint) {
        Rect bounds = new Rect();
        paint.getTextBounds(str, 0, str.length(), bounds);
        float x1 = 0;
        if((DisplayW / 2)>=(bounds.width() / 2)){
            x1=(DisplayW / 2) - ( bounds.width() / 2);
        }
        drawString(canvas, str, DisplayW,x1, y, paint.getTextSize(), paint.getColor(), paint);
        float[] PictureRange = new float[]{x1,x1+bounds.width(),y,y+bounds.height()};
        return PictureRange;
    }

    /*在指定地方畫出字符串 */
    public static void drawString(Canvas canvas, String str, float x, float y, Paint paint) {
        drawString(canvas, str, DisplayW, x, y, paint.getTextSize(), paint.getColor(), paint);
    }

    /*在指定地方畫出字符串 */
    public static void drawString(Canvas canvas, String str, float x, float y, float textSize, int color) {
        Paint paint = new Paint();
        drawString(canvas, str, DisplayW, x, y, textSize, color, paint);
    }

    /*在指定地方畫出字符串 */
    public static void drawString(Canvas canvas, String str, float stringWidth, float x, float y, float textSize, int color) {
        Paint paint = new Paint();
        drawString(canvas, str, stringWidth, x, y, textSize, color, paint);
    }

    /*在指定地方畫出字符串 */
    public static void drawString(Canvas canvas, String str, float stringWidth, float x, float y, float textSize, int color, Paint paint) {
        paint.setColor(color);
        paint.setTextSize(textSize);
        if (stringWidth < (textSize * str.length())) {
            int l = (int) (stringWidth / textSize);
            int r = (int) (str.length() / l) + (int) ((str.length() % l) / (str.length() % l));
            for (int i = 0; i < r; i++) {
                if (str.length() > l) {
                    canvas.drawText(str.substring((int) x, l), x, y + textSize, paint);
                    str = str.substring(l);
                } else {
                    canvas.drawText(str, x, y + textSize, paint);
                }
                y = y + textSize;
            }
        } else {
            canvas.drawText(str, x, y + textSize/2, paint);
        }

        paint = null;
    }
/********************************************************************畫圖*********************************************************************************************/
}
