package com.midco.touch;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;

/**
 * Created by MIDCO on 2015/4/13.
 */
public class GameActivity extends Activity {
    private ThreadCanvas mThreadCanvas = null;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        try{
            new GameMain(this);
            mThreadCanvas = new ThreadCanvas(this);

            //獲取屏幕分辨率
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            //把屏幕分辨率賦值到GameTools以備後用
            GameTools.setDisplayWH(dm.widthPixels, dm.heightPixels);

            setContentView(mThreadCanvas);
			
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    /**
     * 暂停
     *
     * @param N /A
     * @return null
     */
    protected void onPause() {
        super.onPause();
    }


    /**
     * 重绘
     *
     * @param N /A
     * @return null
     */
    protected void onResume() {
        super.onResume();
        mThreadCanvas.requestFocus();
        mThreadCanvas.start();
    }


    /**
     * 按键按下
     *
     * @param N /A
     * @return null
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        mThreadCanvas.onKeyDown(keyCode);
        return false;
    }


    /**
     * 按键弹起
     *
     * @param N /A
     * @return null
     */
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        mThreadCanvas.onKeyUp(keyCode);
        return false;
    }


}
