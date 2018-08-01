package com.example.controller;

import com.example.bean.UserInfo;
import com.example.builder.dao.impl.HbaseAccountInfoMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HbaseAccountInfoService {
    @Autowired
    private HbaseAccountInfoMapperImpl hbaseAccountInfoMapper;

    public UserInfo findUserInfoByEntity(String table, String family, String rowKey, UserInfo userInfo) {

        return hbaseAccountInfoMapper.findUserInfoByEntity(table,family,rowKey,userInfo);
    }


}
