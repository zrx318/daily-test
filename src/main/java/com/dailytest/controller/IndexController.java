package com.dailytest.controller;


import com.dailytest.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequestMapping("/")
@RestController
public class IndexController {
    private List<String> list = new ArrayList<>();
    private int num = 0;

    @Autowired
    @Qualifier("AdminJdbcTemplate")
    private JdbcTemplate adminJdbcTemplate;

    @RequestMapping("/")
    public Object hello() {

        return getAllUserInfo();
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