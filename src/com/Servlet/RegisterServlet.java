package com.Servlet;

import com.Dao.User;
import com.Dao.UserDao;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset = utf-8");
        resp.setHeader("Content-type", "text/html;charset=utf-8");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println(name + " " +password+" "+email);
        User user = new User(name, password, email);
        UserDao userDao = new UserDao(user);
        if(userDao.queryUserByName()){
            resp.getOutputStream().write("用户名已存在".getBytes("utf-8"));
        } else if (userDao.queryUserByEmail()){
            resp.getOutputStream().write("邮箱已存在".getBytes("utf-8"));
        } else{
            System.out.println(user.getEmail()+"fuck");
            if(userDao.insertUser())
                resp.getOutputStream().write("注册成功".getBytes("utf-8"));
            else
                resp.getOutputStream().write("注册失败".getBytes("utf-8"));
        }

    }
}
