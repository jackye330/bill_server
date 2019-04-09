package com.Servlet;

import com.Dao.User;
import com.Dao.UserDao;
import com.Util.EMailUtil;
import org.apache.commons.httpclient.HttpException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendVCServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws HttpException, IOException{
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws HttpException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset = utf-8");
        resp.setHeader("Content-type", "text/html;charset=utf-8");
        String username = req.getParameter("username");
        User user = new User();
        user.setName(username);
        UserDao userDao = new UserDao(user);
        String email = userDao.getUserEMail();
        if (!email.equals("")){
            if (EMailUtil.sendEmail(email)){
                resp.getOutputStream().write("验证码发送成功".getBytes("utf-8"));
            } else{
                resp.getOutputStream().write("验证码发送失败，请检查网络".getBytes("utf-8"));
            }
        } else{
            resp.getOutputStream().write("验证码发送失败".getBytes("utf-8"));
        }
    }
}