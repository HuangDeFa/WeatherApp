package com.kenzz.weatherapp.beans;

/**
 * Created by huangdefa on 15/08/2017.
 */

public class HourForeCast {
    String date;
    int hum;
    int pop;
    int pres;
    float tmp;
    Wind wind;
    Cond cond;

    public HourForeCast() {
    }

    public HourForeCast(String date, int hum, int pop, int pres, float tmp, Wind wind, Cond cond) {
        this.date = date;
        this.hum = hum;
        this.pop = pop;
        this.pres = pres;
        this.tmp = tmp;
        this.wind = wind;
        this.cond=cond;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHum() {
        return hum;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
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

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

   public static class Cond{
        String code;
        private String txt;

        public Cond() {
        }

        public Cond(String code, String txt) {
            this.code = code;
            this.txt = txt;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }
}
