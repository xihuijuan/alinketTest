package com.example.builder.dao;

import com.example.bean.UserInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HbaseAccountInfoDao {
    UserInfo findUserInfoByEntity(String table, String family, String rowKey, UserInfo userInfo);
    List<UserInfo> findByUserInfoList();
    public void deleteById(String id);
    UserInfo saveUserInfo(String id,String userName,Integer age);
    Boolean updataUserInfo(UserInfo userInfo);

}
