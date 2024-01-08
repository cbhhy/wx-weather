package com.codehuan.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSONArray;
import com.codehuan.constant.Constants;
import com.codehuan.entity.vo.GaoDeWeaverVo;
import com.codehuan.util.TokenUtil;
import com.codehuan.util.WXUtil;
import lombok.extern.slf4j.Slf4j;

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
public class WeaverController {

    private static String substringSafe(String input, int beginIndex, int endIndex) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        int length = input.length();
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        if (endIndex > length) {
            endIndex = length;
        }

        // 如果截取的起始位置大于等于结束位置，则返回空字符串
        if (beginIndex >= endIndex) {
            return "";
        }

        return input.substring(beginIndex, endIndex);
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
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
        String text1 = null;
        String text2 = null;
        String text3 = null;
        while (text == null || text.length() <= 30) {
            try {
                text = TokenUtil.gettext();
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        // 分别截取20个字符到text1、text2、text3
        text1 = substringSafe(text, 0, 20);
        text2 = substringSafe(text, 20, 40);
        text3 = substringSafe(text, 40, 60);


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

        JSONObject param13 = new JSONObject();
        param13.put("value", text1);
        param13.put("color", WXUtil.getColor());

        JSONObject param14 = new JSONObject();
        param14.put("value", text2);
        param14.put("color", WXUtil.getColor());

        JSONObject param15 = new JSONObject();
        param15.put("value", text3);
        param15.put("color", WXUtil.getColor());


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
        data.put("words1", param13);
        data.put("words2", param14);
        data.put("words3", param15);
        data.put("windpower", param9);
        data.put("date", param10);
        data.put("week", param11);
        // 用户
        JSONArray users = null;
        //    获取人员信息
        try {
            users = TokenUtil.getFlower();
        } catch (Exception e) {
            log.error("errorInfo:{}", e.getMessage());
        }
        // 发送信息

        assert users != null;
        for (Object user : users) {
            if (user.toString().equals("oJr7d6eZMUilE3trpA53UZSdVJMQ")) {
                WXUtil.sendMsg(user.toString(), "32ngK9-0nX02bsxppFY1mECHZZBVMAucnVAvrRXpBBc", Constants.APP_ID, data);
            }
        }
    }
}
