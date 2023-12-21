package com.codehuan.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.codehuan.entity.Weaver;
import lombok.*;

import java.util.List;

/**
 * @author ZhangHuan created on 2022/8/22
 * @email codehuan@163.com
 */
@Getter
@Setter
public class GaoDeWeaverVo {
    private String status;

    private String count;

    private String info;

    private String infocode;

    private List<Forecast> forecasts;

    @Getter
    @Setter
    public static class Forecast {

        private String city;

        private String adcode;

        private String province;

        @JSONField(name = "reporttime")
        private String reportTime;

        private List<Cast> casts;
    }

    @Getter
    @Setter
    public static class Cast {

        private String date;

        private String week;

        @JSONField(name = "dayweather")
        private String dayWeather;

        @JSONField(name = "nightweather")
        private String nightWeather;

        @JSONField(name = "daytemp")
        private String dayTemp;

        @JSONField(name = "nighttemp")
        private String nightTemp;

        @JSONField(name = "daywind")
        private String dayWind;

        @JSONField(name = "nightwind")
        private String nightWind;

        @JSONField(name = "daypower")
        private String dayPower;

        @JSONField(name = "nightpower")
        private String nightPower;

        @JSONField(name = "daytemp_float")
        private String dayTempFloat;

        @JSONField(name = "nighttemp_float")
        private String nightTempFloat;
    }

}
