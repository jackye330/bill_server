package com.Servlet;

import com.Dao.Record;
import com.Dao.RecordDao;
import com.Dao.User;
import com.Dao.UserDao;
import com.Json.StringToJson;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddRecordServlet extends HttpServlet {
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
        //String record = req.getParameter("record");
        BufferedReader bufferedReader = req.getReader();
        String json = bufferedReader.readLine();
        JSONObject jsonObject = JSONObject.fromObject(json);
        Record record1 = new Record(jsonObject.getInt("uid"), jsonObject.getDouble("amount"),
                                    jsonObject.getString("category"), jsonObject.getInt("type"),
                                    jsonObject.getString("uuid"), jsonObject.getString("date"),
                                    jsonObject.getString("remark"));
//        double amount = Double.parseDouble(req.getParameter("amount"));
//        String category = req.getParameter("category");
//        int uid = Integer.parseInt(req.getParameter("uid"));
//        int recordType = Integer.parseInt(req.getParameter("recordType"));
//        String uuid = req.getParameter("uuid");
//        String date = req.getParameter("date");
//        Record record = new Record(uid,amount,category, recordType, uuid, date);
//        System.out.println(record.getAmount());
        //List<Record> recordList = StringToJson.getJsonRecord(record);
        //System.out.println(recordList.get(0).getRemark());
        //RecordDao recordDao = new RecordDao(recordList.get(0));
        RecordDao recordDao = new RecordDao(record1);
        if(recordDao.addUserRecord()){
            resp.getWriter().write("添加成功");
        }
        else
            resp.getWriter().write("添加失败");
        bufferedReader.close();
    }
}
