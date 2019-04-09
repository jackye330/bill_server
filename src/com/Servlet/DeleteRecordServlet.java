package com.Servlet;

import com.Dao.Record;
import com.Dao.RecordDao;
import com.Dao.User;
import com.Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRecordServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset = utf-8");
        resp.setHeader("Content-type", "text/html;charset=utf-8");
        String uuid = req.getParameter("uuid");
        Record record = new Record();
        record.setUuid(uuid);
        RecordDao recordDao = new RecordDao(record);
        if (recordDao.deleteUserRecord()) {
            resp.getOutputStream().write("删除成功".getBytes("utf-8"));
        } else {
            resp.getOutputStream().write("error".getBytes("utf-8"));
        }

    }
}
