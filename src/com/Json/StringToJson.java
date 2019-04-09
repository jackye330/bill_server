package com.Json;

import com.Dao.Record;
import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class StringToJson {
    public static List<Record> getJsonRecord(String json){
        JSONArray jsonArray = JSONArray.fromObject(json);
        List<Record> recordList = new ArrayList<Record>();
        for(int i = 0; i < jsonArray.size(); ++i){
            Record record = new Record();
            record.setUid(1);
            record.setDate(jsonArray.getJSONObject(i).getString("date"));
            record.setUuid(jsonArray.getJSONObject(i).getString("uuid"));
            record.setCategory(jsonArray.getJSONObject(i).getString("category"));
            record.setType(jsonArray.getJSONObject(i).getInt("type"));
            record.setAmount(jsonArray.getJSONObject(i).getDouble("amount"));
            record.setRemark(jsonArray.getJSONObject(i).getString("remark"));
            recordList.add(record);
        }
        return recordList;
    }
}
