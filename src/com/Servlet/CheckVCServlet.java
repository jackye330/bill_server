package com.Servlet;
import org.apache.commons.httpclient.HttpException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckVCServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws HttpException, IOException{
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws HttpException, IOException{
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset = utf-8");
        resp.setHeader("Content-type", "text/html;charset=utf-8");
        String vc = req.getParameter("vc");
        if (vc.equals(com.Util.VCUtil.vc)){
            resp.getOutputStream().write("验证成功".getBytes("utf-8"));
        } else{
            resp.getOutputStream().write("验证码错误".getBytes("utf-8"));
        }
    }
}
