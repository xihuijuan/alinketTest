package com.example.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HbaseAccountController {

    private final static String TABLE_NAME = "user";

    private final static String FAMILY_INFO = "info";

    @Autowired
    private HbaseAccountInfoService hbaseAccountInfoServiceImpl;
    @RequestMapping(value = "/bigdata/find")
    public String findUserInfoByName(String name, ModelMap modelMap) {
        UserInfo userInfo = hbaseAccountInfoServiceImpl.findUserInfoByEntity(TABLE_NAME, FAMILY_INFO,
                "1", new UserInfo());

        modelMap.addAttribute("userInfo", userInfo);
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println(userInfo.getUserName());
        System.out.println("+++++++++++++++++++++++++++++++++++++");

        return "hbase/hbaseTest";
    }
}