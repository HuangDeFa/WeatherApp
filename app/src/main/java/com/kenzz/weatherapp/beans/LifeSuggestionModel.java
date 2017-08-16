package com.kenzz.weatherapp.beans;

import java.util.List;

/**
 * Created by huangdefa on 15/08/2017.
 */

public class LifeSuggestionModel {
    private BasicAddress basic;
    private String status;
    private LifeSuggestionWrapper suggestion;

    public LifeSuggestionModel() {
    }

    public LifeSuggestionModel(BasicAddress basic, String status, LifeSuggestionWrapper suggestion) {
        this.basic = basic;
        this.status = status;
        this.suggestion = suggestion;
    }

    public BasicAddress getBasic() {
        return basic;
    }

    public void setBasic(BasicAddress basic) {
        this.basic = basic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LifeSuggestionWrapper getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(LifeSuggestionWrapper suggestion) {
        this.suggestion = suggestion;
    }

    public static class LifeSuggestionWrapper {
        private LifeSuggestion comf;
        private LifeSuggestion cw;
        private LifeSuggestion drsg;
        private LifeSuggestion flu;
        private LifeSuggestion sport;
        private LifeSuggestion trav;
        private LifeSuggestion uv;

        public LifeSuggestionWrapper() {
        }

        public LifeSuggestionWrapper(LifeSuggestion comf, LifeSuggestion cw, LifeSuggestion drsg, LifeSuggestion flu, LifeSuggestion sport, LifeSuggestion trav, LifeSuggestion uv) {
            this.comf = comf;
            this.cw = cw;
            this.drsg = drsg;
            this.flu = flu;
            this.sport = sport;
            this.trav = trav;
            this.uv = uv;
        }

        public LifeSuggestion getComf() {
            return comf;
        }

        public void setComf(LifeSuggestion comf) {
            this.comf = comf;
        }

        public LifeSuggestion getCw() {
            return cw;
        }

        public void setCw(LifeSuggestion cw) {
            this.cw = cw;
        }

        public LifeSuggestion getDrsg() {
            return drsg;
        }

        public void setDrsg(LifeSuggestion drsg) {
            this.drsg = drsg;
        }

        public LifeSuggestion getFlu() {
            return flu;
        }

        public void setFlu(LifeSuggestion flu) {
            this.flu = flu;
        }

        public LifeSuggestion getSport() {
            return sport;
        }

        public void setSport(LifeSuggestion sport) {
            this.sport = sport;
        }

        public LifeSuggestion getTrav() {
            return trav;
        }

        public void setTrav(LifeSuggestion trav) {
            this.trav = trav;
        }

        public LifeSuggestion getUv() {
            return uv;
        }

        public void setUv(LifeSuggestion uv) {
            this.uv = uv;
        }

    }

    public static class LifeSuggestionModelWrapper {
        private List<LifeSuggestionModel> HeWeather5;

        public List<LifeSuggestionModel> getHeWeather5() {
            return HeWeather5;
        }

        public void setHeWeather5(List<LifeSuggestionModel> heWeather5) {
            HeWeather5 = heWeather5;
        }
    }

    public static class LifeSuggestionModelWrapper{
        private List<LifeSuggestionModel> HeWeather5;

        public List<LifeSuggestionModel> getHeWeather5() {
            return HeWeather5;
        }

        public void setHeWeather5(List<LifeSuggestionModel> heWeather5) {
            HeWeather5 = heWeather5;
        }
    }
}
