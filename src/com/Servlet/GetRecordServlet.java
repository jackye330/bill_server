package com.Servlet;

import com.Dao.Record;
import com.Dao.RecordDao;
import com.Dao.User;
import com.Dao.UserDao;
import com.Json.JsonToString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

public class GetRecordServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        int uid = Integer.parseInt(req.getParameter("uid"));
        System.out.println(uid);
        Record record = new Record();
        record.setUid(uid);
        RecordDao recordDao = new RecordDao(record);
        List<Record> recordList = recordDao.getUserRecordByUid();
        //通过json返回
        if(recordList != null && recordList.size() != 0){
            resp.getOutputStream().write(JsonToString.recordJson(recordList).getBytes());
        }
    }
}
