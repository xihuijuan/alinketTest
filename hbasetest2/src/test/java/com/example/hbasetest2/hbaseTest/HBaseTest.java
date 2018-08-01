package com.example.hbasetest2.hbaseTest;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HBaseTest {
    public static Configuration configuration;

    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "127.0.0.1");
        configuration.set("hbase.master", "127.0.0.1:8080");
    }

    public static void main(String[] args) {
        // createTable("test");
        //insertData("test");
        //deleteRow("test","1111111");
       // queryAll("test");
      //  queryByRowKey("test");
        updateById("test");
    }

    public static void createTable(String tableName) {
        System.out.println("start create table");
        try {
            HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
            if (hBaseAdmin.tableExists(tableName)) {
                hBaseAdmin.disableTable(tableName);
                hBaseAdmin.deleteTable(tableName);
                System.out.println("table exists delete table");
            }
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            hTableDescriptor.addFamily(new HColumnDescriptor("test_id"));
            hTableDescriptor.addFamily(new HColumnDescriptor("info"));
            hTableDescriptor.addFamily(new HColumnDescriptor("address"));
            hBaseAdmin.createTable(hTableDescriptor);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end create table....");

    }

    public static void insertData(String tableName) {
        System.out.println("Start insert data......");
        // HTablePool pool = new HTablePool(configuration, 10);
        try {
            HTable table = new HTable(configuration, tableName);
            Put put = new Put("3".getBytes());
            put.addColumn("info".getBytes(), "name".getBytes(), "zhangsan".getBytes());
            put.addColumn("info".getBytes(), "age".getBytes(), "23".getBytes());
            put.addColumn("address".getBytes(), "city".getBytes(), "shanghai".getBytes());

            table.put(put);
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end insert data....");
    }

    public static void deleteRow(String tableName, String rowKey) {
        try {
            HTable table = new HTable(configuration, tableName);
            List list = new ArrayList<>();
            Delete delete = new Delete(rowKey.getBytes());
            list.add(delete);
            table.delete(list);
            System.out.println("delete row success");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void queryAll(String tableName){
        try {
            HTable table = new HTable(configuration,tableName);
            ResultScanner resultScanner = table.getScanner(new Scan());
            for(Result r:resultScanner){
                System.out.println("获得到rowkey:" + new String(r.getRow()));
                for(KeyValue keyValue:r.raw()){
                    System.out.println("列：" + new String(keyValue.getFamily())
                            + "====值:" + new String(keyValue.getValue()));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void queryByRowKey(String tableName){
        try {
            HTable table = new HTable(configuration,tableName);
            Get scan = new Get("3".getBytes());
            Result r = table.get(scan);
            System.out.println("获得的rowkey:"+ new String(r.getRow()));
            for(KeyValue keyValue : r.raw()){
                System.out.println("列:"+new String(keyValue.getFamily())+"====值："+new String(keyValue.getValue()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateById(String tableName){
        try {
            HTable hTable = new HTable(configuration,tableName);
            //Put中放置行的值
            Put p = new Put("2".getBytes());
            //add（列族名，列名，要更新的值）
            p.add("info".getBytes(),"age".getBytes(),"25".getBytes());
            hTable.put(p);
            System.out.println("data updated success");
            hTable.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
