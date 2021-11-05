package com.dailytest.controller;


import com.alibaba.fastjson.JSONObject;
import com.dailytest.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Slf4j
//@RequestMapping("/")
@RestController
public class IndexController {
    private List<String> list = new ArrayList<>();
    private int num = 0;

    @Autowired
    @Qualifier("AdminJdbcTemplate")
    private JdbcTemplate adminJdbcTemplate;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/insert")
    public String insertWt(@RequestParam("wt1") String wt1,
                           @RequestParam("da1") String da1) {
        JSONObject result = new JSONObject();
        log.error("==========111-========");

        result.put("status", "success");
        result.put("wt", "test1");
        result.put("wt", "test2");
        return result.toJSONString();
    }
    @RequestMapping("/select")
    public String selectWt() {
        JSONObject result = new JSONObject();
        log.error("==========111-========");
        System.out.println("1111");

        result.put("status", "success");
        result.put("wt", "test1");
        result.put("wt", "test2");
        return result.toJSONString();
    }

    private List<UserInfo> getAllUserInfo() {
        List<UserInfo> userInfos = adminJdbcTemplate.query("select * from t_user", (resultSet, i) -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(resultSet.getLong("id"));
            userInfo.setName(resultSet.getString("name"));
            userInfo.setPassword(resultSet.getString("password"));
            return userInfo;
        });
        return userInfos;
    }
}