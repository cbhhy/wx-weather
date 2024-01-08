package com.codehuan.task;

import com.alibaba.fastjson2.JSONArray;
import com.codehuan.constant.Constants;
import com.codehuan.service.EveningService;
import com.codehuan.util.TokenUtil;
import com.codehuan.util.WXUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 计划任务
 *
 * @author ZhangHuan created on 2022/8/22
 */
@Slf4j
@Component
public class WXTask {
    @Resource
    EveningService eveningService;


    //@Scheduled(cron = "0 */1 *  * * ? ")
    @Scheduled(cron = "00 30 6 ? * MON-FRI")
    public void morningWeekday() {
        log.info("开始执行定时任务!!!");
        Map<String, Object> data = eveningService.encapsulation();
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
            WXUtil.sendMsg(user.toString(), "O9khaHBRDyUe8ogTiAyOfW_wsxPeel1YTjnhCBeVeIg", Constants.APP_ID, data);
        }
    }

    @Scheduled(cron = "00 30 7 ? * SAT-SUN")
    public void morningWeekend() {
        log.info("开始执行定时任务!!!");
        Map<String, Object> data = eveningService.encapsulation();
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
            WXUtil.sendMsg(user.toString(), "O9khaHBRDyUe8ogTiAyOfW_wsxPeel1YTjnhCBeVeIg", Constants.APP_ID, data);
        }
    }

//        
//
//    }


    //@Scheduled(cron = "0 */1 *  * * ? ")
    @Scheduled(cron = "00 30 22 * * ? ")
    public void evening() {
        log.info("开始执行定时任务!!!");
        Map<String, Object> data = eveningService.encapsulation();
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
            WXUtil.sendMsg(user.toString(), "oE4j8KCuwSwG4Qe8v-X9b0xR3Ao0mZmuueLJcn5id1s", Constants.APP_ID, data);
        }
    }
}
