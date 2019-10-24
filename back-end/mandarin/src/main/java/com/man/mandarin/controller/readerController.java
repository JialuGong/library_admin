package com.man.mandarin.controller;

import com.alibaba.fastjson.JSON;
import com.man.mandarin.entity.Admin_rules;
import com.man.mandarin.service.AdminService;
import com.man.mandarin.service.LibrarianService;
import com.man.mandarin.service.RuleService;
import com.man.mandarin.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/apis/mandarin/reader")
public class readerController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private RuleService ruleService;
    //1.获取图书借阅期限
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
    //2.获取读者缴纳金
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
    //3.获取图书罚款的金额
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
