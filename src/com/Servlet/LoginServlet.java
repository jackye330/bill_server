package com.Servlet;

import com.Dao.User;
import com.Dao.UserDao;
import com.Json.JsonToString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //get请求
        this.doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //post请求

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset = utf-8");
        resp.setHeader("Content-type", "text/html;charset=utf-8");
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        System.out.println(account + " " +password);
        String pattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        boolean isMatch = Pattern.matches(pattern, account);
        User user = new User();
        if (isMatch){
            //验证邮箱密码
            user.setEmail(account);
            user.setPassword(password);
            UserDao userDao = new UserDao(user);
            if(userDao.loginUserByEmail()){
                resp.getOutputStream().write(JsonToString.userJson(userDao.getUser()).getBytes("utf-8"));
            }
            else
                resp.getOutputStream().write(JsonToString.userJson(new User()).getBytes("utf-8"));
        } else {
            //验证用户名密码
            user.setName(account);
            user.setPassword(password);
            UserDao userDao = new UserDao(user);
            if(userDao.loginUserByName()){
                resp.getOutputStream().write(JsonToString.userJson(userDao.getUser()).getBytes("utf-8"));
            }
            else
                resp.getOutputStream().write(JsonToString.userJson(new User()).getBytes("utf-8"));
        }
    }
}
