package com.midco.touch;
import android.content.*;
import android.graphics.*;
import android.view.*;
import java.util.*;

public class GameOver extends GameView
{

	//private String m_Tag = "ThreadCanvas_Tag";
    private GameMain mMainGame = null;
    private Canvas mCanvas = null;
	private Paint mPaint = new Paint();
	private int Checkpoint,Score;
    private boolean pMusic = true;//第一次進入播放音樂
	ArrayList<float[]> ItemRange = new ArrayList<float[]>();//用於保存選項顯示範圍

	public GameOver(Context context, GameMain mainGame, int Checkpoint, int Score)
	{
		super(context);
        mMainGame = mainGame;
		this.Checkpoint = Checkpoint;
		this.Score = Score;
		//this.tempNum=tempNum;
	}

	public void onDraw(Canvas canvas)
	{
        if (mMainGame.mbMusic == 0)
		{
            mMainGame.mbMusic = 1;
            mMainGame.bgPlayer.PlayMusic((int) (Math.random() * 8) + 1);
            pMusic = false;
        }
		mCanvas = canvas;
		mCanvas.drawColor(Color.BLACK);
        mPaint.setAntiAlias(true);//設置為無鋸齒
        mPaint.setStyle(Paint.Style.FILL);//畫筆實心

        mPaint.setTextSize(GameTools.getDisplayW() / 12);
		int hCenter=(int)GameTools.getDisplayH() / 2;
		mPaint.setColor(Color.RED);//設置畫筆顏色
		GameTools.drawString(mCanvas, "GAME OVER", hCenter - mPaint.getTextSize(), mPaint);
		mPaint.setColor(Color.YELLOW);//設置畫筆顏色
		GameTools.drawString(mCanvas, "Level:" + Checkpoint, hCenter + (mPaint.getTextSize() * 0.2f), mPaint);
		mPaint.setColor(Color.GREEN);//設置畫筆顏色
        GameTools.drawString(mCanvas, "Score:" + Score, hCenter + mPaint.getTextSize() + (mPaint.getTextSize() * 0.4f), mPaint);
		mPaint.setColor(Color.CYAN);//設置畫筆顏色
		ItemRange.add(GameTools.drawString(mCanvas, "Replay", hCenter + mPaint.getTextSize() + mPaint.getTextSize() * 5, mPaint));


	}

    /**
     * 按键按下
     *
     * @param        N/A
     * @return null
     */
    public boolean onKeyDown(int keyCode)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{

			mMainGame.bgPlayer.StopMusic();
			mMainGame.mbMusic = 0;
            mMainGame.controlView(1);
        }
		return false;
	}
    /**
     * 按键弹起
     *
     * @param        N/A
     * @return null
     */
    public boolean onKeyUp(int keyCode)
	{
		return false;
	}

    /**
     * 回收资源
     */
    public void reCycle()
	{

	}

    /**
     * 刷新
     */
    public void refurbish()
	{

	}

    /**
     * 點擊坐標
     * */
    public boolean onTouchEvent(MotionEvent event)
	{

		if(ItemRange.size()!=0){
			//判斷是否點擊了開始遊戲選項
			if ((event.getRawY() >= ItemRange.get(0)[2]) && (event.getRawY() <= ItemRange.get(0)[3]))
			{
				if ((event.getRawX() >= ItemRange.get(0)[0]) && (event.getRawX() <= ItemRange.get(0)[1]))
				{
					mMainGame.bgPlayer.StopMusic();
					mMainGame.mbMusic = 0;
					mMainGame.controlView(2);
				}
			}
		}
		

		return false;
	}
}
