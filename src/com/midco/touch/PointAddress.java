package com.midco.touch;

/**
 * Created by MIDCO on 2015/4/10.
 */
public class PointAddress {
    private int x;
    private int y;
    private int sartP;
    public PointAddress(int x, int y,int sartP) {
        this.x = x;
        this.y = y;
		this.sartP=sartP;
    }

    public int getPointX() {
        return x;
    }

    public int getPointY() {
        return y;
    }
	public int getSartP(){
		return sartP;
	}
}
