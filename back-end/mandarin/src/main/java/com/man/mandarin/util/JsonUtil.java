package com.man.mandarin.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.man.mandarin.entity.Admin;
import com.man.mandarin.entity.Admin_rules;
import com.man.mandarin.entity.Librarian;
import java.util.List;

public class JsonUtil {

    public JsonUtil() {
    }
    public JSON messagetoJson(String status,String error_type){
        JSONObject jsonObject = new JSONObject();
        if(status=="success"){
            jsonObject.put("code",1);
            jsonObject.put("message","success");
            jsonObject.put("data",null);
        }else if(status=="fail"){
            jsonObject.put("code",0);
            jsonObject.put("message",error_type);
            jsonObject.put("data",null);
        }
        return jsonObject;
    }
    public JSON admintoJson(String status,List<Admin> admins,String error_type){
        JSONObject jsonObject = new JSONObject();
            if(status=="success"){
                jsonObject.put("code",1);
                jsonObject.put("message","success");
                jsonObject.put("data",adtoJson(admins));
            }else if(status=="fail"){
                jsonObject.put("code",0);
                jsonObject.put("message",error_type);
                jsonObject.put("data",null);
            }
            return jsonObject;
    }
    public JSON librariantoJson(String status,List<Librarian> librarians,String error_type){
        JSONObject jsonObject = new JSONObject();
        if(status=="success"){
            jsonObject.put("code",1);
            jsonObject.put("message","success");
            jsonObject.put("data",libtoJson(librarians));
        }else if(status=="fail"){
            jsonObject.put("code",0);
            jsonObject.put("message",error_type);
            jsonObject.put("data",null);
        }
        return jsonObject;
    }
    public JSON ruletoJson(String status,List<Admin_rules> rules,String error_type,String info){
        JSONObject jsonObject = new JSONObject();
        if(status=="success"){
            jsonObject.put("code",1);
            jsonObject.put("message","success");
            jsonObject.put("data",rltoJson(rules,info));
        }else if(status=="fail"){
            jsonObject.put("code",0);
            jsonObject.put("message",error_type);
            jsonObject.put("data",null);
        }
        return jsonObject;
    }
    //返回超级管理员json信息
    public JSON adtoJson(List<Admin> admins){
        JSONArray json = new JSONArray();
        JSONObject jsonObject = null;
        for (Admin admin : admins){
        jsonObject=new JSONObject();
        jsonObject.put("admin_id", admin.getId());
        jsonObject.put("admin_name", admin.getName());
        jsonObject.put("admin_password", admin.getPassword());
        jsonObject.put("admin_phone", admin.getPhone());
        jsonObject.put("admin_email", admin.getEmail());
        json.add(jsonObject);
    }
    return json;
}
//返回图书管理员Json信息
    public JSON libtoJson(List<Librarian> librarians){
        JSONArray json = new JSONArray();
        JSONObject jsonObject = null;
        for (Librarian librarian : librarians){
            jsonObject=new JSONObject();
            jsonObject.put("librarian_id", librarian.getId());
            jsonObject.put("librarian_name", librarian.getName());
            jsonObject.put("librarian_password", librarian.getPassword());
            jsonObject.put("librarian_phone", librarian.getPhone());
            jsonObject.put("librarian_email", librarian.getEmail());
            json.add(jsonObject);
        }
        return json;
    }
    //返回rule的json信息
    public JSON rltoJson(List<Admin_rules> rules,String info){
        JSONArray json = new JSONArray();
        JSONObject jsonObject = null;
        if(info=="fine"){
            for (Admin_rules rule : rules){
                jsonObject=new JSONObject();
                if(rule.getFine()>0){
                    jsonObject.put("book_fine_value", rule.getFine());
                    jsonObject.put("timestamp", rule.getTimestamp());
                    json.add(jsonObject);
                }else
                    continue;
            }
        }else if(info=="period"){
            for (Admin_rules rule : rules){
                jsonObject=new JSONObject();
                if(rule.getReturn_period()>0){
                    jsonObject.put("book_period", rule.getReturn_period());
                    jsonObject.put("timestamp", rule.getTimestamp());
                    json.add(jsonObject);
                }else
                    continue;
            }
        }else if(info=="deposit"){
            for (Admin_rules rule : rules){
                jsonObject=new JSONObject();
                if (rule.getDeposit()>0){
                    jsonObject.put("reader_deposit", rule.getDeposit());
                    jsonObject.put("timestamp", rule.getTimestamp());
                    json.add(jsonObject);
                }else
                    continue;
            }
        }else
            json=null;
        return json;
    }
}
