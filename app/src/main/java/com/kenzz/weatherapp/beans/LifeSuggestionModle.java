package com.kenzz.weatherapp.beans;

/**
 * Created by huangdefa on 15/08/2017.
 */

public class LifeSuggestionModle {
    private BasicAddress basic;
    private String status;
    private LifeSuggestionWraper suggestion;

    public LifeSuggestionModle() {
    }

    public LifeSuggestionModle(BasicAddress basic, String status, LifeSuggestionWraper suggestion) {
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

    public LifeSuggestionWraper getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(LifeSuggestionWraper suggestion) {
        this.suggestion = suggestion;
    }

    static class LifeSuggestionWraper{
        private LifeSuggestion comf;
        private LifeSuggestion cw;
        private LifeSuggestion drsg;
        private LifeSuggestion flu;
        private LifeSuggestion sport;
        private LifeSuggestion trav;
        private LifeSuggestion uv;

        public LifeSuggestionWraper() {
        }

        public LifeSuggestionWraper(LifeSuggestion comf, LifeSuggestion cw, LifeSuggestion drsg, LifeSuggestion flu, LifeSuggestion sport, LifeSuggestion trav, LifeSuggestion uv) {
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
}
