package com.midco.touch;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import java.util.*;

/**
 * Created by MIDCO on 2015/4/17.
 */
public class GameRunning extends GameView
{
    private String m_Tag = "ThreadCanvas_Tag";
    private GameMain mMainGame = null;
    private Canvas mCanvas = null;
	private Paint mPaint = new Paint();
	private ArrayList<PointAddress> aPointAddress= new ArrayList<PointAddress>();//點坐標


    private int CircleR = 0;//圓半徑
    private int CircleWC = 5;//圓圈橫數個數
    private int CircleHC = 0;//圓圈縱數個數
    private int pointX = 0;//當前繪製圓坐標x
    private int pointY = 0;//當前繪製圓坐標y

    private int Checkpoint = 0;//關卡 
    private int Score = 0;//分數
    private int TouchPoint = 5;//隨機出現的可觸點
	private int TouchPointTemp=0;//臨時
	private int isTouchPoint =0;//已經點過的紅圈數量


	private int tempNum=0;//用於計算剩餘時間
    private boolean istrue = true;//用於判斷玩家有否點錯
	private int timeT=1000 / GameTools.getRefurbishTime();//計算出每秒刷新次數
	private int timeOver = 4;
    private int sleepT = timeT * timeOver;//屏幕紅圈刷新週期，數字為秒
    private int countT = sleepT;//線程訪問次數
	private int CheckpointTime = (sleepT / timeT) - 1;//關卡剩餘時間
    private boolean pMusic = true;//第一次進入播放音樂
    Bitmap mSCBitmap = null;  /* 创建一个缓冲区 */
	private int arcW=360;//紅圈值
	private int arcP=360 / sleepT;//畫圓平均值
//	private int arcSize=0;//紅圈初始大小
//	private int arcPS=0;//紅圈半徑平均每次增長

	private int arcRC=0;
	private int arcBC=0;

//

    /**
     * 刷新
     */
    @Override
    protected void refurbish()
	{

    }

    public GameRunning(Context context, GameMain mainGame)
	{
        super(context);
        mMainGame = mainGame;

    }

    //記錄點坐標
    public void getPoint()
	{
        pointX = (int) (Math.random() * CircleWC) + 1;
        pointY = (int) (Math.random() * (CircleHC - 1)) + 2;//+2是因為需要空出最上一行他用途，-1防止出界
    }

    /**
     * 绘图
     *
     * @param canvas@return null
     */
    @Override
    protected void onDraw(Canvas canvas)
	{
        if (mMainGame.mbMusic == 0)
		{
            mMainGame.mbMusic = 1;
            mMainGame.bgPlayer.PlayMusic((int) (Math.random() * 8) + 1);
            pMusic = false;
        }


        mCanvas = canvas;

        CircleR = (int) GameTools.getDisplayW() / (CircleWC * 2);//計算出遊戲圓圈半徑
        CircleHC = (int) GameTools.getDisplayH() / (CircleR * 2);//計算出遊戲中，縱向圓圈數
//		arcSize =(int) GameTools.getDisplayW() / ((CircleWC * 2)*2);//計算出遊戲圓圈半徑
//		arcPS =(CircleR-arcSize)/sleepT;//計算出圓直徑增長平均值


        DrawBG();

        if (countT == sleepT)
		{
			if (Checkpoint > 0)
			{//判斷玩家是否消除了當前關卡所有紅圈
				if (aPointAddress.size() > 0)
				{
					GameOver();
				}
				else
				{
					arcW = 360;
				}
			}
            //附加條件
			checkpointAction();
			Checkpoint++;//關卡數
            //獲取可點擊圓坐標
            getTouchPoint();
            countT = 0;

        }
		//時間倒數
		CheckpointTime = (sleepT / timeT) - 1;//關卡剩餘時間
		tempNum = countT / timeT;//計算出已經用的時間
		tempNum = CheckpointTime - tempNum;//計算出剩餘時間
        countT++;


        //在相對位置畫出圓
        for (int z = 0; z < aPointAddress.size(); z++)
		{
			mPaint.setStyle(Paint.Style.FILL);//畫筆實心
			//設置畫筆為紅色
			mPaint.setColor(arcBC);
            int x = aPointAddress.get(z).getPointX();
            int y = aPointAddress.get(z).getPointY();
//			mPaint.setColor(Color.LTGRAY);
//			mCanvas.drawCircle(CircleR * (x + x - 1), CircleR * (y + y - 1), CircleR, mPaint);
//			

			RectF  oval = new  RectF(); //RectF對象
			oval.left = CircleR * (x + x - 2) ; //左邊  
			oval.top =  CircleR * (y + y - 2); //上邊  
			oval.right = CircleR * (x + x) ; //右邊  
			oval.bottom = CircleR * (y + y) ; //下邊  
			canvas.drawArc(oval, 0, arcW, true, mPaint); //繪製圓弧  

			mPaint.setStyle(Paint.Style.STROKE);//畫筆空心
			//設置畫筆為紅色
			mPaint.setColor(Color.BLACK);
			mPaint.setStrokeWidth(15); //設置畫筆畫出線的粗細
            mCanvas.drawCircle(CircleR * (x + x - 1), CircleR * (y + y - 1), CircleR - 15, mPaint);
			//mCanvas.drawCircle(CircleR * (x + x - 1), CircleR * (y + y - 1), arcSize+arcPS*countT, mPaint);
        }
		arcW = arcW - arcP;//圓倒數遞減


    }

    //附加條件
    public void checkpointAction()
	{
        TouchPointTemp++;
		if (Checkpoint <= 9)
		{
			arcRC = Color.WHITE;
			arcBC = Color.MAGENTA;
			timeOver = 4;
		}

        if (TouchPointTemp == 10 && TouchPointTemp <= 100)
		{
            TouchPoint++;//增加紅圈數
			TouchPointTemp = 0;
        }

		if (Checkpoint == 10)
		{
			arcRC = Color.WHITE;
			arcBC = Color.YELLOW;
		}

		if (Checkpoint == 30)
		{
			arcRC = Color.WHITE;
			arcBC = Color.RED;
			timeOver = 3;
		}

		if (Checkpoint == 70)
		{
			arcRC = Color.WHITE;
			arcBC = Color.GREEN;
			timeOver = 2;
		}

		sleepT = timeT * timeOver;//屏幕紅圈刷新週期，數字為秒
		arcP = 360 / sleepT;//計算紅圈填充比例


    }

    //畫出背景
    public void DrawBG()
	{
        mCanvas.drawColor(Color.BLACK);
        mPaint.setAntiAlias(true);//設置為無鋸齒
        mPaint.setStyle(Paint.Style.FILL);//畫筆實心
		mPaint.setStrokeWidth(4); //設置畫筆畫出線的粗細

        mPaint.setTextSize(GameTools.getDisplayW() / 22);
		mPaint.setColor(Color.CYAN);//設置畫筆顏色
		GameTools.drawString(mCanvas, "Level:" + Checkpoint, mPaint.getTextSize(), CircleR - (mPaint.getTextSize() / 2), mPaint);
		mPaint.setColor(Color.BLUE);//設置畫筆顏色
		GameTools.drawString(mCanvas, "Score:" + Score, (GameTools.getDisplayW() / 2) - mPaint.getTextSize() * 3, CircleR - (mPaint.getTextSize() / 2), mPaint);
		mPaint.setColor(Color.RED);//設置畫筆顏色
        GameTools.drawString(mCanvas, "Time:" + tempNum, GameTools.getDisplayW() - mPaint.getTextSize() * 6, CircleR - (mPaint.getTextSize() / 2), mPaint);
		mPaint.setColor(arcRC);//設置畫筆顏色
		mPaint.setStyle(Paint.Style.STROKE);//畫筆空心
		mPaint.setStrokeWidth(15); //設置畫筆畫出線的粗細
        //畫出圖形陣列
        for (int y = 2; y <= CircleHC; y++)
		{
            for (int x = 1; x <= CircleWC; x++)
			{
                //在相對位置畫出圓
                mCanvas.drawCircle(CircleR * (x + x - 1), CircleR * (y + y - 1), CircleR - 15, mPaint);
            }
        }
    }

    //獲取可點擊圓圈坐標
    public void getTouchPoint()
	{


        for (int z = 0; z < TouchPoint; z++)
		{


            getPoint();//隨機獲得點坐標


            //檢查是否有重複
            if (z != 0)
			{
                for (int j = 0; j < z; j++)
				{
					PointAddress p=aPointAddress.get(j);
                    if ((p.getPointX() == pointX) && (p.getPointY() == pointY))
					{
                        getPoint();
                        j = 0;
                    }
                }
            }

            //記錄點坐標
			aPointAddress.add(new PointAddress(pointX, pointY));


        }
    }

    /**
     * 按键按下
     *
     * @param keyCode@return null
     */
    @Override
    public boolean onKeyDown(int keyCode)
	{
		//mMainGame.bgPlayer.playsound();
        if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			mMainGame.bgPlayer.StopMusic();
			mMainGame.mbMusic = 0;
            mMainGame.controlView(1);
        }
        Log.i("keyDown", "x=" + keyCode);
        return false;
    }

    /**
     * 按键弹起
     *
     * @param keyCode@return null
     */
    @Override
    public boolean onKeyUp(int keyCode)
	{
        Log.i("KeyUp", "x=" + keyCode);
        return false;
    }

    /**
     * 回收资源
     */
    @Override
    protected void reCycle()
	{

    }

    /**
     * 點擊坐標
     *
     * @param event
     */
    @Override
    public boolean onTouchEvent(MotionEvent event)
	{

		// mMainGame.touchPlayer.FreeMusic();
		mMainGame.bgPlayer.playsound();
		int x, y;
		//mPaint.setColor(Color.YELLOW);//設置畫筆為白色
		for (int z = 0; z < aPointAddress.size(); z++)
		{

			x = aPointAddress.get(z).getPointX();
			y = aPointAddress.get(z).getPointY();
			//判斷點擊X範圍
			if ((event.getRawX() >= CircleR * (x + x - 2)) && (event.getRawX() <= CircleR * (x + x)))
			{
				if ((event.getRawY() >= CircleR * (y + y - 2)) && (event.getRawY() <= CircleR * (y + y)))
				{
					//在相對位置畫出圓
					aPointAddress.remove(z);

					Score += 100;//每個點加100分
					//mCanvas.drawCircle(CircleR * (x + x - 1), CircleR * (y + y - 1), CircleR, mPaint);
					isTouchPoint++;
					if (isTouchPoint == TouchPoint)
					{//判斷是否全部紅點都點完
						countT = sleepT;//直接跳到下一關

						isTouchPoint = 0;//恢復原始狀態
					}
					istrue = false;//如果點對了就不跳進GameOver界面
				}
			}
		}
		//mMainGame.touchPlayer.PlayMusic(1);
		if (istrue)
		{//玩家點錯處理
			GameOver();
		}
		istrue = true;//還原默認值
		Log.i(m_Tag, "x=" + event.getRawX() + " && y=" + event.getRawY());
		

        return true;
    }

	public void GameOver()
	{
		mMainGame.Checkpoint = Checkpoint;
		mMainGame.Score = Score;
		mMainGame.bgPlayer.StopMusic();
		mMainGame.mbMusic = 0;
		mMainGame.controlView(3);
		//mMainGame.touchPlayer.FreeMusic();

	}

}
