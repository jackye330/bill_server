package com.Servlet;

import com.Dao.User;
import com.Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserServlet extends HttpServlet {

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
        String password = req.getParameter("password");
        System.out.println(name + " " +password);
        User user = new User(name, password);
        UserDao userDao = new UserDao(user);
        if(userDao.updateUser())
            resp.getOutputStream().write("修改密码成功".getBytes("utf-8"));
        else
            resp.getOutputStream().write("error".getBytes("utf-8"));


    }
}
