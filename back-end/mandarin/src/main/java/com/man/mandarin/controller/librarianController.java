package com.man.mandarin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.man.mandarin.entity.Admin_rules;
import com.man.mandarin.entity.Librarian;
import com.man.mandarin.service.LibrarianService;
import com.man.mandarin.service.RuleService;
import com.man.mandarin.util.JsonUtil;
import com.man.mandarin.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/apis/mandarin/librarian")
public class librarianController {
    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private RuleService ruleService;
    // 1.登录
    @CrossOrigin(methods = RequestMethod.POST)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSON login(
            @RequestParam("librarian_name") String name,
            @RequestParam("librarian_password") String password) {
        JsonUtil jsonUtil =new JsonUtil();
        try{
            if (name != null && password != null) {
                List<Librarian> librarians = librarianService.queryByName(name);
                if (librarians.size() == 0) {
                    return jsonUtil.messagetoJson("fail","name_not_found");
                } else {
                    if (librarians.get(0).getPassword().equals(password)) {
                        JSONObject jsonObject=new JSONObject();
                        //for token
                        JSONObject tokenJsonObject=new JSONObject();
                        Librarian librarian= new Librarian();
                        String token = TokenUtil.sign(librarian);
                        tokenJsonObject.put("token",token);
                        jsonObject.put("code",1);
                        jsonObject.put("message","success");
                        jsonObject.put("data",tokenJsonObject);
                        return jsonObject;
                    } else {
                        return jsonUtil.messagetoJson("fail","password_error");
                    }
                }
            }else
                return null;
        }catch (Exception e){
            return jsonUtil.messagetoJson("fail","login_fail");
        }
    }
//2.获取图书借阅期限
@CrossOrigin(methods = RequestMethod.GET)
@RequestMapping(value = "/getReturnPeriod", method = RequestMethod.GET)
@ResponseBody
public JSON getBookPeriod() {
    JsonUtil jsonUtil=new JsonUtil();
    List<Admin_rules> rules= ruleService.getAllInfo();
    try{
        return jsonUtil.ruletoJson("success",rules,null,"period");
    }catch (Exception e){
        return jsonUtil.ruletoJson("fail",rules,"acquisition_failed",null);
    }
}
    //3.获取读者缴纳金
    @CrossOrigin(methods = RequestMethod.GET)
    @RequestMapping(value = "/getDeposit", method = RequestMethod.GET)
    @ResponseBody
    public JSON getReaderDeposit() {
        JsonUtil jsonUtil=new JsonUtil();
        List<Admin_rules> rules= ruleService.getAllInfo();
        try{
            return jsonUtil.ruletoJson("success",rules,null,"deposit");
        }catch (Exception e){
            return jsonUtil.ruletoJson("fail",rules,"acquisition_failed",null);
        }
    }
    //4.获取图书罚款的金额
    @CrossOrigin(methods = RequestMethod.GET)
    @RequestMapping(value = "/getFineValue", method = RequestMethod.GET)
    @ResponseBody
    public JSON getBookFine() {
        JsonUtil jsonUtil=new JsonUtil();
        List<Admin_rules> rules=ruleService.getAllInfo();
        try{
            return jsonUtil.ruletoJson("success",rules,null,"fine");
        }catch (Exception e){
            return jsonUtil.ruletoJson("fail",rules,"acquisition_failed",null);
        }
    }
}
