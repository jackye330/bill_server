package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordDao {
    private Record record;

    public RecordDao(Record record){
        this.record = new Record(record.getUid(),record.getAmount(), record.getCategory(), record.getType(), record.getUuid(), record.getDate(), record.getRemark());
    }

    //根据用户id查找用户账单
    public List<Record> getUserRecordByUid(){
        Connection conn = new DBConnection().getConnection();
        String sql = "select * from record where uid = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Record> recordList = new ArrayList<Record>();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, record.getUid());
            rs = pstmt.executeQuery();
            while(rs.next()){
                Record record = new Record(rs.getInt("uid"), rs.getDouble("amount"), rs.getString("category"), rs.getInt("record_type"), rs.getString("uuid"), rs.getString("date"), rs.getString("remark"), rs.getTimestamp("time").getTime());
                System.out.println(rs.getTimestamp("time").getTime());
                recordList.add(record);
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return recordList;
    }

    //添加账单信息
    public boolean addUserRecord(){
        Connection conn = new DBConnection().getConnection();
        String sql = "insert into record(uid,amount,category,record_type,uuid,date,remark) values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, record.getUid());
            pstmt.setDouble(2, record.getAmount());
            pstmt.setString(3, record.getCategory());
            pstmt.setInt(4, record.getType());
            pstmt.setString(5, record.getUuid());
            pstmt.setString(6, record.getDate());
            pstmt.setString(7,record.getRemark());
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    //删除账单信息
    public boolean deleteUserRecord(){
        Connection conn = new DBConnection().getConnection();
        String sql = "delete from record where uuid = ?";
        PreparedStatement pstmt = null;
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,record.getUuid());
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
