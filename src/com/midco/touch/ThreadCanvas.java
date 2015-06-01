package com.midco.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by MIDCO on 2015/4/13.
 */
public class ThreadCanvas extends View implements Runnable {
    private String m_Tag = "ThreadCanvas_Tag";
    private int gameLoop = GameTools.getRefurbishTime();//遊戲界面刷新間隔時間


    public ThreadCanvas(Context context) {
        super(context);
    }


    /**
     * 绘图
     *
     * @param N /A
     * @return null
     */
    protected void onDraw(Canvas canvas) {
        if (GameMain.getMainView() != null) {
            GameMain.getMainView().onDraw(canvas);
        } else {
            Log.i(m_Tag, "null");
        }
    }


    /**
     * 绘图显示
     */
    public void start() {
        Thread t = new Thread(this);
        t.start();
    }


    // 刷新界面
    public void refurbish() {
        if (GameMain.getMainView() != null) {
            GameMain.getMainView().refurbish();
        }
    }


    /**
     * 游戏循环
     *
     * @param N /A
     * @return null
     */
    public void run() {
        while (true) {
            try {
                Thread.sleep(gameLoop);
            } catch (Exception e) {
                e.printStackTrace();
            }

            refurbish(); // 更新显示
            postInvalidate(); // 刷新屏幕
        }
    }


    // 按键处理(按键按下)
    boolean onKeyDown(int keyCode) {
        if (GameMain.getMainView() != null) {
            GameMain.getMainView().onKeyDown(keyCode);
        } else {
            Log.i(m_Tag, "null");
        }
        return true;
    }


    // 按键弹起
    boolean onKeyUp(int keyCode) {
        if (GameMain.getMainView() != null) {
            GameMain.getMainView().onKeyUp(keyCode);
        } else {
            Log.i(m_Tag, "null");
        }
        return true;
    }

    /**
     * 點擊坐標
     *
     * @param event
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (GameMain.getMainView() != null) {
            GameMain.getMainView().onTouchEvent(event);
        } else {
            Log.i(m_Tag, "null");
        }
        return false;
    }
}
