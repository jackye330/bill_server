package com.Dao;

import java.io.IOException;
import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.Util.RSAUtil;

import static com.Util.RSAUtil.base642Byte;

public class UserDao {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user.setId(user.getId());
        this.user.setName(user.getName());
        this.user.setPassword(user.getPassword());
        this.user.setEmail(user.getEmail());
    }

    public UserDao(User user){
        this.user = new User(user.getName(), user.getPassword(), user.getEmail());
    }

    public boolean loginUserByName(){
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        String sql = "select * from user where uname=?";
        try {
            PrivateKey privateKey = RSAUtil.keyStrToPrivate(RSAUtil.PRIVATE_KEY_STR);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getName());
            ResultSet rs = pstmt.executeQuery();
            if(rs != null && rs.next()){
                user.setEmail(rs.getString("email"));
                user.setId(rs.getInt("uid"));
                String decrypted = new String(RSAUtil.privateDecrypt(base642Byte(rs.getString("psw")), privateKey));
                if(user.getPassword().equals(decrypted)){
                    pstmt.close();
                    rs.close();
                    dbConnection.close();
                    return true;
                }
                else{
                    pstmt.close();
                    rs.close();
                    dbConnection.close();
                    return false;
                }

            }
            pstmt.close();
            rs.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean loginUserByEmail(){
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        String sql = "select * from user where email=?";
        try {
            PrivateKey privateKey = RSAUtil.keyStrToPrivate(RSAUtil.PRIVATE_KEY_STR);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getEmail());
            ResultSet rs = pstmt.executeQuery();
            if(rs != null && rs.next()){
                user.setName(rs.getString("uname"));
                user.setId(rs.getInt("uid"));
                String decrypted = new String(RSAUtil.privateDecrypt(base642Byte(rs.getString("psw")), privateKey));
                if(user.getPassword().equals(decrypted)){
                    pstmt.close();
                    rs.close();
                    dbConnection.close();
                    return true;
                }
                else{
                    pstmt.close();
                    rs.close();
                    dbConnection.close();
                    return false;
                }

            }
            pstmt.close();
            rs.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean queryUserByName(){
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        String sql = "select * from user where uname=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getName());
            rs = pstmt.executeQuery();
            if(rs != null && rs.next()){
                pstmt.close();
                rs.close();
                dbConnection.close();
                return true;
            }
            pstmt.close();
            rs.close();
            dbConnection.close();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean queryUserByEmail(){
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        String sql = "select * from user where email=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            rs = pstmt.executeQuery();
            if(rs != null && rs.next()){
                pstmt.close();
                rs.close();
                dbConnection.close();
                return true;
            }
            pstmt.close();
            rs.close();
            dbConnection.close();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertUser(){
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        String sql = "insert into user(uname,psw,email)values(?,?,?)";
        if(user.getName() == null || user.getPassword() == null || user.getName() == "" || user.getPassword() == "")
            return false;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getName());
            pstmt.setString(2,user.getPassword());
            pstmt.setString(3,user.getEmail());
            pstmt.executeUpdate();
            if(pstmt != null)
                pstmt.close();
            dbConnection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(){
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        String sql = "update user set psw=? where uname=?";
        PreparedStatement pstmt = null;
        if(user.getName() == null || user.getPassword() == null || user.getName() == "" || user.getPassword() == "")
            return false;
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getName());
            pstmt.setString(2,user.getPassword());
            pstmt.executeUpdate();
            if(pstmt != null)
                pstmt.close();
            dbConnection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUserEMail(){
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        String sql = "select email from user where uname=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        if (user.getName() == null || user.getName() == ""){
            return "";
        }
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getName());
            rs = pstmt.executeQuery();
            if(rs != null && rs.next()){
                return rs.getString("email");
            }
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
        return "";
    }
}
