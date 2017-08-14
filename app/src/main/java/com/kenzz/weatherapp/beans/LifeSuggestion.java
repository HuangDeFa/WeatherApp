package com.kenzz.weatherapp.beans;

/**
 * Created by huangdefa on 15/08/2017.
 */

public class LifeSuggestion {
    private String brf;
    private String txt;

    public LifeSuggestion() {
    }

    public LifeSuggestion(String brf, String txt) {
        this.brf = brf;
        this.txt = txt;
    }

    public String getBrf() {
        return brf;
    }

    public void setBrf(String brf) {
        this.brf = brf;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
