package com.midco.touch;

import java.io.IOException;

import android.app.ExpandableListActivity;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.AudioManager;

/**
 * Created by MIDCO on 2015/4/13.
 */
public class CMIDIPlayer {
    public MediaPlayer playerMusic;

    public GameActivity gameMain = null;
    public SoundPool soundPool;

    public int alertId;


    public CMIDIPlayer(GameActivity gameMain) {
        this.gameMain = gameMain;
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        alertId = soundPool.load(gameMain, R.raw.pushing_a_key, 1);
    }


    // 播放音乐
    public void PlayMusic(int ID) {
        FreeMusic();
        switch (ID) {
            case 1:
                //装载音乐
                playerMusic = MediaPlayer.create(gameMain, R.raw.fringe_trimmed_boots);

                //设置循环
                playerMusic.setLooping(true);
                try {
                    //准备
                    playerMusic.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //开始
                playerMusic.start();
                break;
            case 2:
                playerMusic = MediaPlayer.create(gameMain, R.raw.fringe_trimmed_boots);
                playerMusic.setLooping(true);
                try {
                    playerMusic.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                playerMusic.start();
                break;
            case 3:
                playerMusic = MediaPlayer.create(gameMain, R.raw.fringe_trimmed_boots);
                playerMusic.setLooping(true);
                try {
                    playerMusic.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                playerMusic.start();
                break;
            case 4:
                playerMusic = MediaPlayer.create(gameMain, R.raw.fringe_trimmed_boots);
                playerMusic.setLooping(true);
                try {
                    playerMusic.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                playerMusic.start();
                break;
            case 5:
                playerMusic = MediaPlayer.create(gameMain, R.raw.fringe_trimmed_boots);
                playerMusic.setLooping(true);
                try {
                    playerMusic.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                playerMusic.start();
                break;
            case 6:
                playerMusic = MediaPlayer.create(gameMain, R.raw.fringe_trimmed_boots);
                playerMusic.setLooping(true);
                try {
                    playerMusic.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                playerMusic.start();
                break;
            case 7:
                playerMusic = MediaPlayer.create(gameMain, R.raw.fringe_trimmed_boots);
                playerMusic.setLooping(true);
                try {
                    playerMusic.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                playerMusic.start();
                break;
        }
    }

    public void playsound() {
        try {

            soundPool.play(alertId, 1.0F, 1.0F, 1, 1, 0.5F);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 退出释放资源
    public void FreeMusic() {
        try {
            if (playerMusic != null) {
                playerMusic.stop();
                playerMusic.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // 停止播放
    public void StopMusic() {
        try {
            if (playerMusic != null) {
                playerMusic.stop();

                playerMusic.reset();
                //playerMusic.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
