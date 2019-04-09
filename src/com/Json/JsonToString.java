package com.Json;

import com.Dao.Record;
import com.Dao.User;
import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonToString {

    public static String recordJson(List<Record> recordList){
        JSONArray jsonArray = new JSONArray();
        for(Record record:recordList){
            jsonArray.add(record);
        }
        return jsonArray.toString();
    }

    public static String userJson(User user){
        Map params = new HashMap();
        params.put("uid", user.getId());
        params.put("name", user.getName());
        params.put("password", user.getPassword());
        params.put("email", user.getEmail());
        JSONArray jsonArray = JSONArray.fromObject(params);
        return jsonArray.toString();
    }
}

