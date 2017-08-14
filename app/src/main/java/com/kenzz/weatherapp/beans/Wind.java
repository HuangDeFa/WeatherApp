package com.kenzz.weatherapp.beans;

/**
 * Created by huangdefa on 15/08/2017.
 */

public class Wind {
    int deg;
    String dir;
    String sc;
    int spd;

    public Wind() {
    }

    public Wind(int deg, String dir, String sc, int spd) {
        this.deg = deg;
        this.dir = dir;
        this.sc = sc;
        this.spd = spd;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }
}
