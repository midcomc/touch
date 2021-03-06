package com.midco.touch;

import android.content.*;
import android.graphics.*;
import android.view.*;
import java.util.*;

/**
 * Created by Administrator on 2015/4/22.
 */
public class GameMenu extends GameView {

    private String m_Tag = "ThreadCanvas_Tag";
    private GameMain mMainGame = null;
    private Canvas mCanvas = null;
    private Paint mPaint = new Paint();
    ArrayList<float[]> ItemRange = new ArrayList<float[]>();//用於保存選項顯示範圍
    private boolean pMusic = true;//第一次進入播放音樂

    public GameMenu(Context context, GameMain mainGame) {
        super(context);
        mMainGame = mainGame;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mMainGame.mbMusic==0){
            mMainGame.mbMusic = 1;
            mMainGame.bgPlayer.PlayMusic((int) (Math.random() *8) + 1);
            pMusic=false;
        }
		
		
		
        mCanvas = canvas;
        mCanvas.drawColor(Color.BLACK);
        mPaint.setAntiAlias(true);//設置為無鋸齒
        mPaint.setStyle(Paint.Style.FILL);//畫筆實心
        
        mPaint.setTextSize(GameTools.getDisplayW() / 12);
        int hCenter = (int) GameTools.getDisplayH() / 2;
		mPaint.setColor(Color.CYAN);//設置畫筆顏色
        ItemRange.add(GameTools.drawString(mCanvas, "Play Game", hCenter - mPaint.getTextSize(), mPaint));
		mPaint.setColor(Color.RED);//設置畫筆顏色
        ItemRange.add(GameTools.drawString(mCanvas, "Exit", hCenter + (mPaint.getTextSize() * 0.2f), mPaint));
		
//		mPaint.setStyle(Paint.Style.FILL);//畫筆空心
//		mPaint.setColor(Color.RED);
//		mPaint.setStrokeWidth(1); //設置畫筆畫出線的粗細
//		RectF  oval = new  RectF(); //RectF對象
//		int x=1,y=1,CircleR=360;
//		
//		oval.left = CircleR * (x + x - 2) ; //左邊  
//		oval.top =  CircleR * (y + y - 2); //上邊  
//		oval.right = CircleR * (x + x) ; //右邊  
//		oval.bottom = CircleR * (y + y) ; //下邊  
//		for(int z=1;z<360;z++){
//			canvas.drawArc(oval, z, 1, true, mPaint); //繪製圓弧 
//		}
		 

    }

    @Override
    public boolean onKeyDown(int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            System.exit(0);
        }
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode) {
        return false;
    }

    @Override
    protected void reCycle() {

    }

    @Override
    protected void refurbish() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //判斷是否點擊了開始遊戲選項
        if ((event.getRawY() >= ItemRange.get(0)[2]) && (event.getRawY() <= ItemRange.get(0)[3])) {
            if ((event.getRawX() >= ItemRange.get(0)[0]) && (event.getRawX() <= ItemRange.get(0)[1])) {
				mMainGame.bgPlayer.StopMusic();
				mMainGame.mbMusic = 0;
                mMainGame.controlView(2);
            }
        }
        //判斷是否點擊了退出遊戲
        if ((event.getRawY() >= ItemRange.get(1)[2]) && (event.getRawY() <= ItemRange.get(1)[3])) {
            if ((event.getRawX() >= ItemRange.get(1)[0]) && (event.getRawX() <= ItemRange.get(1)[1])) {
                System.exit(0);
            }
        }


        return true;
    }
}
