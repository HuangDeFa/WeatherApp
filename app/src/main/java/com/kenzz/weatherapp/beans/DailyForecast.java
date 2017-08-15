package com.kenzz.weatherapp.beans;

/**
 * Created by huangdefa on 15/08/2017.
 */

public class DailyForecast {
    Astor astro;
    Cond cond;
    String date;
    int hum;
    float pcpn;
    int pop;
    int pres;
    Temperature Temperature;
    int uv;
    int vis;
    Wind Wind;

    public DailyForecast() {
    }

    public DailyForecast(Astor astro, Cond cond, String date, int hum, float pcpn, int pop, int pres, DailyForecast.Temperature temperature, int uv, int vis, com.kenzz.weatherapp.beans.Wind wind) {
        this.astro = astro;
        this.cond = cond;
        this.date = date;
        this.hum = hum;
        this.pcpn = pcpn;
        this.pop = pop;
        this.pres = pres;
        Temperature = temperature;
        this.uv = uv;
        this.vis = vis;
        Wind = wind;
    }

    public Astor getAstro() {
        return astro;
    }

    public void setAstro(Astor astro) {
        this.astro = astro;
    }

    public Cond getCond() {
        return cond;
    }

    public void setCond(Cond cond) {
        this.cond = cond;
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

    public float getPcpn() {
        return pcpn;
    }

    public void setPcpn(float pcpn) {
        this.pcpn = pcpn;
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

    public DailyForecast.Temperature getTemperature() {
        return Temperature;
    }

    public void setTemperature(DailyForecast.Temperature temperature) {
        Temperature = temperature;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public int getVis() {
        return vis;
    }

    public void setVis(int vis) {
        this.vis = vis;
    }

    public com.kenzz.weatherapp.beans.Wind getWind() {
        return Wind;
    }

    public void setWind(com.kenzz.weatherapp.beans.Wind wind) {
        Wind = wind;
    }

   public static class Astor {
        private String mr;
        private String ms;
        private String sr;
        private String ss;

        public Astor() {
        }

        public Astor(String mr, String ms, String sr, String ss) {
            this.mr = mr;
            this.ms = ms;
            this.sr = sr;
            this.ss = ss;
        }

        public String getMr() {
            return mr;
        }

        public void setMr(String mr) {
            this.mr = mr;
        }

        public String getMs() {
            return ms;
        }

        public void setMs(String ms) {
            this.ms = ms;
        }

        public String getSr() {
            return sr;
        }

        public void setSr(String sr) {
            this.sr = sr;
        }

        public String getSs() {
            return ss;
        }

        public void setSs(String ss) {
            this.ss = ss;
        }
    }

   public static class Cond {
        int code_d;
        int code_n;
        String text_d;
        String text_n;

        public Cond() {
        }

        public Cond(int code_d, int code_n, String text_d, String text_n) {
            this.code_d = code_d;
            this.code_n = code_n;
            this.text_d = text_d;
            this.text_n = text_n;
        }

        public int getCode_d() {
            return code_d;
        }

        public void setCode_d(int code_d) {
            this.code_d = code_d;
        }

        public int getCode_n() {
            return code_n;
        }

        public void setCode_n(int code_n) {
            this.code_n = code_n;
        }

        public String getText_d() {
            return text_d;
        }

        public void setText_d(String text_d) {
            this.text_d = text_d;
        }

        public String getText_n() {
            return text_n;
        }

        public void setText_n(String text_n) {
            this.text_n = text_n;
        }
    }

   public static class Temperature {
        float max;
        float min;

        public Temperature() {
        }

        public Temperature(float max, float min) {
            this.max = max;
            this.min = min;
        }

        public float getMax() {
            return max;
        }

        public void setMax(float max) {
            this.max = max;
        }

        public float getMin() {
            return min;
        }

        public void setMin(float min) {
            this.min = min;
        }
    }
}
