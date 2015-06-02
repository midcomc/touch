package com.midco.touch;

import android.app.Activity;
import android.content.Context;

/**
 * Created by MIDCO on 2015/4/13.
 */
public class GameMain {
    private static GameView m_GameView = null;        // 当前需要显示的对象
    private Context m_Context = null;
    private GameActivity m_GameMain = null;
    private int m_status = 0;        //游戏状态
    public CMIDIPlayer bgPlayer;
    public CMIDIPlayer touchPlayer;
    public byte mbMusic = 0;
	public int Checkpoint,Score;//記錄關卡與分數用
	
    public GameMain(Context context) {
        m_Context = context;
        m_GameMain = (GameActivity) context;
        m_status = -1;

        initGame();
    }

    //初始化游戏
    public void initGame() {
        controlView(1);
        bgPlayer = new CMIDIPlayer(m_GameMain);
        touchPlayer = new CMIDIPlayer(m_GameMain);
    }

    //得到游戏状态
    public int getStatus() {
        return m_status;
    }

    //设置游戏状态
    public void setStatus(int status) {
        m_status = status;
    }

    //得到主类对象
    public Activity getGameMain() {
        return m_GameMain;
    }

    //得到当前需要显示的对象
    public static GameView getMainView() {
        return m_GameView;
    }

    //控制显示什么界面
    public void controlView(int status) {
        if (m_status != status) {
            if (m_GameView != null) {
                m_GameView.reCycle();
                System.gc();
            }
        }
        freeGameView(m_GameView);
        switch (status) {
            case 1:
                m_GameView = new GameMenu(m_Context, this);
                break;
            case 2:
                m_GameView = new GameRunning(m_Context, this);
                break;
            case 3:
                m_GameView = new GameOver(m_Context,this,Checkpoint,Score);
                break;
        }
        setStatus(status);
    }

    //释放界面对象
    public void freeGameView(GameView gameView) {
        if (gameView != null) {
            gameView = null;
            System.gc();
        }
    }
}
