package com.kenzz.weatherapp.beans;

import java.util.List;

/**
 * Created by huangdefa on 15/08/2017.
 */

public class AddressModel {
    private BasicAddress basic;
    private String status;

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

    public static class AddressModelWrapper{
        private List<AddressModel> HeWeather5;

        public List<AddressModel> getHeWeather5() {
            return HeWeather5;
        }

        public void setHeWeather5(List<AddressModel> heWeather5) {
            HeWeather5 = heWeather5;
        }
    }
}
