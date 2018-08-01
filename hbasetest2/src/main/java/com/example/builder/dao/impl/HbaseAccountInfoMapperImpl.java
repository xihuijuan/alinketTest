package com.example.builder.dao.impl;

import com.example.bean.UserInfo;
import com.example.builder.HbaseFindBuilder;
import com.example.builder.dao.HbaseAccountInfoDao;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public class HbaseAccountInfoMapperImpl<T> implements HbaseAccountInfoDao{

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private Class<T> clz;

    public Class<T> getClz() {
        if (clz == null) {
            // 获取泛型的Class对象
            clz = ((Class<T>) (((ParameterizedType) (this.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[0]));
        }
        return clz;
    }


    public UserInfo findUserInfoByEntity(String table, String family, String rowKey, UserInfo userInfo) {

        return (UserInfo) hbaseTemplate.get(table, rowKey, family,
                (result, rowNum) -> new HbaseFindBuilder<>(family, result, userInfo.getClass()).build("userName","age","id").fetch());
    }

    @Override
    public List<UserInfo> findByUserInfoList() {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public UserInfo saveUserInfo(String id, String userName, Integer age) {
        return null;
    }

    @Override
    public Boolean updataUserInfo(UserInfo userInfo) {
        return null;
    }
}