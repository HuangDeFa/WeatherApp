package com.kenzz.weatherapp.beans;

/**
 * Created by huangdefa on 15/08/2017.
 */

public class NowWeather {
    private HourForeCast.Cond cond;
    private int fl;
    private int hum;
    private float pcpn;
    private int pres;
    private float tmp;
    private int vis;
    private Wind wind;

    public NowWeather() {
    }

    public NowWeather(HourForeCast.Cond cond, int fl, int hum, float pcpn, int pres, float tmp, int vis, Wind wind) {
        this.cond = cond;
        this.fl = fl;
        this.hum = hum;
        this.pcpn = pcpn;
        this.pres = pres;
        this.tmp = tmp;
        this.vis = vis;
        this.wind = wind;
    }

    public HourForeCast.Cond getCond() {
        return cond;
    }

    public void setCond(HourForeCast.Cond cond) {
        this.cond = cond;
    }

    public int getFl() {
        return fl;
    }

    public void setFl(int fl) {
        this.fl = fl;
    }

    public int getHum() {
        return hum;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }

    public float getPcpn() {
        return pcpn;
    }

    public void setPcpn(float pcpn) {
        this.pcpn = pcpn;
    }

    public int getPres() {
        return pres;
    }

    public void setPres(int pres) {
        this.pres = pres;
    }

    public float getTmp() {
        return tmp;
    }

    public void setTmp(float tmp) {
        this.tmp = tmp;
    }

    public int getVis() {
        return vis;
    }

    public void setVis(int vis) {
        this.vis = vis;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
