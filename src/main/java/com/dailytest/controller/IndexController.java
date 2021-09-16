package com.dailytest.controller;


import com.dailytest.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequestMapping("/")
@RestController
public class IndexController {
    private List<String> list = new ArrayList<>();
    private int num = 0;

    @Autowired
    @Qualifier("TagInfoJdbcTemplate")
    private JdbcTemplate tagInfoJdbcTemplate;

    @RequestMapping("/")
    public Object hello() {
//        list.add("hello: " + (num++));
//        return list;
        log.info("==========1===========");
        List<UserInfo> userInfos = tagInfoJdbcTemplate.queryForList("select * from t_user", UserInfo.class);
        log.info("=========2=========");
        return userInfos;
    }
}
