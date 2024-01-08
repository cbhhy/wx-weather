package com.codehuan.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.codehuan.entity.vo.GaoDeWeaverVo;
import com.codehuan.util.TokenUtil;
import com.codehuan.util.WXUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangHuan created on 2022/8/22
 * @email codehuan@163.com
 */

@Slf4j
@Service
public class MorningService {

    public Map<String, Object> encapsulation() {
        //    获取天气信息
        GaoDeWeaverVo weaver = null;
        try {
            weaver = TokenUtil.getGaoDeWeaver();
        } catch (Exception e) {
            log.error("errorInfo:{}", e.getMessage());
        }
        // 计算生日，
        int birthday = TokenUtil.getNextBirthday("01-27");

        // 在一起多少天
        Long days = TokenUtil.getCountDays("2023-11-11");

        // 获取星期
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String currSun = dateFm.format(date);

        // 毒鸡汤
        String text = null;
        while (text == null || text.length() > 21) {
            try {
                text = TokenUtil.gettext();
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        // 参数封装
        JSONObject param = new JSONObject();
        assert weaver != null;
        param.put("value", weaver.getForecasts().get(0).getCasts().get(0).getDayWeather());
        param.put("color", WXUtil.getColor());

        JSONObject param2 = new JSONObject();
        param2.put("value", weaver.getForecasts().get(0).getCasts().get(0).getNightTemp());
        param2.put("color", WXUtil.getColor());

        JSONObject param12 = new JSONObject();
        param12.put("value", weaver.getForecasts().get(0).getCasts().get(0).getDayTemp() + " ℃");
        param12.put("color", WXUtil.getColor());

        JSONObject param3 = new JSONObject();
        param3.put("value", "深圳市" + weaver.getForecasts().get(0).getCity());
        param3.put("color", WXUtil.getColor());

        JSONObject param4 = new JSONObject();
        param4.put("value", weaver.getForecasts().get(0).getCasts().get(0).getDayWind());
        param4.put("color", WXUtil.getColor());

        JSONObject param5 = new JSONObject();
        param5.put("value", days);
        param5.put("color", WXUtil.getColor());

        JSONObject param6 = new JSONObject();
        param6.put("value", birthday);
        param6.put("color", WXUtil.getColor());

//        JSONObject param7 = new JSONObject();
//        param7.put("value", weaver.getHumidity() + "%");
//        param7.put("color", WXUtil.getColor());

        JSONObject param8 = new JSONObject();
        param8.put("value", text);
        param8.put("color", WXUtil.getColor());

        JSONObject param9 = new JSONObject();
        param9.put("value", weaver.getForecasts().get(0).getCasts().get(0).getDayPower());
        param9.put("color", WXUtil.getColor());

        JSONObject param10 = new JSONObject();
        param10.put("value", DateUtil.format(new Date(), DatePattern.NORM_DATE_FORMATTER));
        param10.put("color", WXUtil.getColor());

        JSONObject param11 = new JSONObject();
        param11.put("value", currSun);
        param11.put("color", WXUtil.getColor());

        Map<String, Object> data = new HashMap<>();
        data.put("weather", param);
        data.put("nighttemp", param2);
        data.put("daytemp", param12);
        data.put("cityname", param3);
        data.put("winddirection", param4);
        data.put("loveDays", param5);
        data.put("birthday_left", param6);
//        data.put("humidity", param7);
        data.put("words", param8);
        data.put("windpower", param9);
        data.put("date", param10);
        data.put("week", param11);
        return data;
    }

}
