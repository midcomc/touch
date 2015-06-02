package com.midco.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by MIDCO on 2015/4/13.
 */
public abstract class GameView extends View {
    public GameView(Context context) {
        super(context);
    }

    /**
     * 绘图
     *
     * @param        N/A
     * @return null
     */
    protected abstract void onDraw(Canvas canvas);

    /**
     * 按键按下
     *
     * @param        N/A
     * @return null
     */
    public abstract boolean onKeyDown(int keyCode);

    /**
     * 按键弹起
     *
     * @param        N/A
     * @return null
     */
    public abstract boolean onKeyUp(int keyCode);

    /**
     * 回收资源
     */
    protected abstract void reCycle();

    /**
     * 刷新
     */
    protected abstract void refurbish();

    /**
     * 點擊坐標
     * */
    public abstract boolean onTouchEvent(MotionEvent event);
}
