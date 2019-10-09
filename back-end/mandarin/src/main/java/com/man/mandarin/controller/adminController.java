package com.man.mandarin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.man.mandarin.entity.Admin;
import com.man.mandarin.entity.Admin_rules;
import com.man.mandarin.entity.Librarian;
import com.man.mandarin.service.AdminService;
import com.man.mandarin.service.LibrarianService;
import com.man.mandarin.service.RuleService;
import com.man.mandarin.util.JsonUtil;
import com.man.mandarin.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping("/apis/mandarin/admin")
public class adminController {
@Autowired
private AdminService adminService;
@Autowired
private LibrarianService librarianService;
@Autowired
private RuleService ruleService;
// 1.登录
@RequestMapping(value = "/login", method = RequestMethod.POST)
@ResponseBody
public JSON login(
@RequestParam("admin_name") String name,
@RequestParam("admin_password") String password) {
    JsonUtil jsonUtil =new JsonUtil();
        try{
        if (name != null && password != null) {
        List<Admin> admins = adminService.queryByName(name);
        if (admins.size() == 0) {
            return jsonUtil.messagetoJson("fail","name_not_found");
        } else {
        if (admins.get(0).getPassword().equals(password)) {
            JSONObject jsonObject=new JSONObject();
            //for token
            JSONObject tokenJsonObject=new JSONObject();
            Admin admin= new Admin();
            String token = TokenUtil.sign(admin);
            tokenJsonObject.put("token",token);
            jsonObject.put("code",1);
            jsonObject.put("message","success");
            jsonObject.put("data",tokenJsonObject);
            return jsonObject;
//            return jsonUtil.messagetoJson("success",null);
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

//2.注册图书管理员
@RequestMapping(value = "/registerLib", method = RequestMethod.POST)
@ResponseBody
public JSON registerLib(
        @RequestParam("librarian_name") String name,
@RequestParam("librarian_password") String password,
@RequestParam("librarian_phone") String phone,
@RequestParam("librarian_email") String email){
    JsonUtil jsonUtil =new JsonUtil();
        try{
        if (name != null && password != null) {
        List<Librarian> librarians= librarianService.queryByName(name);
        if (librarians.size() != 0) {
            return jsonUtil.messagetoJson("fail","name_existed");
        } else {
        librarianService.addLibrarian(name, password, phone, email);
            return jsonUtil.messagetoJson("success",null);
        }
        }else
        return null;
        }catch (Exception e){
            return jsonUtil.messagetoJson("fail","register_fail");
        }
        }
// 3.显示所有图书管理员信息
@RequestMapping(value = "/getAllLib", method = RequestMethod.GET)
@ResponseBody
public JSON getAllLib() {
        try{
        List<Librarian> librarians= librarianService.getAllInfo();
            JsonUtil jsonutil=new JsonUtil();
            return jsonutil.librariantoJson("success",librarians,null);
        } catch (JSONException e) {
        e.printStackTrace();
        return null;
        }
        }

// 4.更改所有图书管理员信息
@RequestMapping(value = "/editLib", method = RequestMethod.POST)
@ResponseBody
public JSON editLib(
@RequestParam("librarian_id") int id,
@RequestParam("librarian_name") String name,
@RequestParam("librarian_password") String password,
@RequestParam("librarian_phone") String phone,
@RequestParam("librarian_email") String email) {
    JsonUtil jsonUtil=new JsonUtil();
        try{
        if (name != null && password != null) {
        List<Librarian> librarians = librarianService.queryByName(name);
        if (librarians.size()!=0&&librarians.get(0).getId()!=id) {
            return jsonUtil.messagetoJson("fail","name_existed");
        } else
            return jsonUtil.messagetoJson("success",null);
        }else
        return null;
        }catch (Exception e){
            return jsonUtil.messagetoJson("fail","edit_fail");
        }
        }

//5.删除图书管理员
@RequestMapping(value = "/deleteLib", method = RequestMethod.POST)
@ResponseBody
public JSON deleteLib(
@RequestParam("librarian_name") String name) {
    JsonUtil jsonUtil=new JsonUtil();
        try{
        List<Librarian> librarians = librarianService.queryByName(name);
        if (librarians.size()!=0){
        librarianService.deleteLibByName(name);
            return jsonUtil.messagetoJson("success",null);
        }else
            return jsonUtil.messagetoJson("fail","name_not_found");
        }catch (Exception e){
            return jsonUtil.messagetoJson("fail","delete_fail");
        }
        }

//6.更改图书罚款的金额
@RequestMapping(value = "/modifyBookFine", method = RequestMethod.POST)
@ResponseBody
public JSON modifyBookFine(
@RequestParam("book_fine_value") double fine,
@RequestParam("timestamp") String timestamp) {
    JsonUtil jsonUtil=new JsonUtil();
        try{
        ruleService.addRule(fine,-1.0,-1.0,timestamp);
            return jsonUtil.messagetoJson("success",null);
        }catch (Exception ignored){
            return jsonUtil.messagetoJson("fail","modify_fail");
        }
        }

//7.获取图书罚款的金额
@RequestMapping(value = "/getBookFine", method = RequestMethod.GET)
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

//8.更改图书借阅期限
@RequestMapping(value = "/modifyBookPeriod", method = RequestMethod.POST)
@ResponseBody
public JSON modifyBookPeriod(
@RequestParam("book_period") double period,
@RequestParam("timestamp") String timestamp) {
    JsonUtil jsonUtil=new JsonUtil();
        try{
        ruleService.addRule(-1.0,period,-1.0,timestamp);
            return jsonUtil.messagetoJson("success",null);
        }catch (Exception e){
            return jsonUtil.messagetoJson("fail","modify_fail");
        }
        }

//9.获取图书借阅期限
@RequestMapping(value = "/getBookPeriod", method = RequestMethod.GET)
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

//10.更改读者缴纳金
@RequestMapping(value = "/modifyReaderDeposit", method = RequestMethod.POST)
@ResponseBody
public JSON modifyReaderDeposit(
@RequestParam("reader_deposit") double deposit,
@RequestParam("timestamp") String timestamp) {
    JsonUtil jsonUtil=new JsonUtil();
        try{
        ruleService.addRule(-1.0,-1.0,deposit,timestamp);
            return jsonUtil.messagetoJson("success",null);
        }catch (Exception e){
            return jsonUtil.messagetoJson("fail","modify_fail");
        }
        }

//11.获取读者缴纳金
@RequestMapping(value = "/getReaderDeposit", method = RequestMethod.GET)
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
        //12.搜索图书管理员
    @RequestMapping(value = "/searchLib", method = RequestMethod.POST)
    @ResponseBody
    public JSON searchLib(
            @RequestParam("librarian_name") String name) {
        List<Librarian> librarians = librarianService.queryByName(name);
        JsonUtil jsonUtil=new JsonUtil();
        try{
            if (librarians.size()!=0){
                return jsonUtil.librariantoJson("success",librarians,null);
            }else
                return jsonUtil.librariantoJson("fail",librarians,"name_not_found");
        }catch (Exception e){
            return jsonUtil.librariantoJson("fail",librarians,"search_fail");
        }
    }
    //13.获得permission
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSON getInfo() {
        JsonUtil jsonUtil=new JsonUtil();
        List<Admin> admins = adminService.getAllInfo();
        try{
            return jsonUtil.admintoJson("success",admins,null);
        }catch (Exception e){
            return jsonUtil.admintoJson("fail",admins,"acquisition_failed");
        }
    }
        }