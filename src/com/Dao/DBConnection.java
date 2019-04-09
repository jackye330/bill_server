package com.Dao;

import java.sql.*;

//连接数据库
public class DBConnection {

        private String url = "jdbc:mysql://localhost:3306/bill_record?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false";
        private String user = "root";
        private String pwd = "123456";
        private Connection conn = null;

        public DBConnection(){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(url, user, pwd);
            } catch(ClassNotFoundException e){
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //创建连接
        public Connection getConnection(){
            return conn;
        }

        public void close(){
            try {
                if(conn!=null){
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }


}
