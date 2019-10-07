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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
+
@Controller
@RequestMapping("/mandarin/admin")
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
public String login(
@RequestParam("admin_name") String name,
@RequestParam("admin_password") String password) {
        try{
        if (name != null && password != null) {
        List<Admin> admins = adminService.queryByName(name);
        if (admins.size() == 0) {
        return "name_not_found";
        } else {
        if (admins.get(0).getPassword().equals(password)) {
        return "login_success";
        } else {
        return "password_error";
        }
        }
        }else
        return null;
        }catch (Exception e){
        return "login_fail";
        }
        }

//2.注册图书管理员
@RequestMapping(value = "/registerLib", method = RequestMethod.POST)
@ResponseBody
public String registerLib(@RequestParam("librarian_name") String name,
@RequestParam("librarian_password") String password,
@RequestParam("librarian_phone") String phone,
@RequestParam("librarian_email") String email){
        try{
        if (name != null && password != null) {
        List<Librarian> librarians= librarianService.queryByName(name);
        if (librarians.size() != 0) {
        return "name_existed";
        } else {
        librarianService.addLibrarian(name, password, phone, email);
        return "register_success";
        }
        }else
        return null;
        }catch (Exception e){
        return "register_fail";
        }
        }
// 3.显示所有图书管理员信息
@RequestMapping(value = "/getAllLib", method = RequestMethod.GET)
@ResponseBody
public JSON getAllLib() {
        try{
        List<Librarian> librarians= librarianService.getAllInfo();
        JSONArray librariansJSON = new JSONArray();
        JSONObject jsonObject = null;
        for (Librarian librarian : librarians){
        jsonObject=new JSONObject();
        jsonObject.put("librarian_id", librarian.getId());
        jsonObject.put("librarian_name", librarian.getName());
        jsonObject.put("librarian_password", librarian.getPassword());
        jsonObject.put("librarian_phone", librarian.getPhone());
        jsonObject.put("librarian_email", librarian.getEmail());
        librariansJSON.add(jsonObject);
        }
        return librariansJSON;
        } catch (JSONException e) {
        e.printStackTrace();
        return null;
        }
        }

// 4.更改所有图书管理员信息
@RequestMapping(value = "/editLib", method = RequestMethod.POST)
@ResponseBody
public String editLib(
@RequestParam("librarian_id") int id,
@RequestParam("librarian_name") String name,
@RequestParam("librarian_password") String password,
@RequestParam("librarian_phone") String phone,
@RequestParam("librarian_email") String email) {
        try{
        if (name != null && password != null) {
        List<Librarian> librarians = librarianService.queryByName(name);
        if (librarians.size()!=0&&librarians.get(0).getId()!=id) {
        return "name_existed";
        } else return "edit_success";
        }else
        return null;
        }catch (Exception e){
        return "edit_fail";
        }
        }

//5.删除图书管理员
@RequestMapping(value = "/deleteLib", method = RequestMethod.POST)
@ResponseBody
public String deleteLib(
@RequestParam("librarian_name") String name) {
        try{
        List<Librarian> librarians = librarianService.queryByName(name);
        if (librarians.size()!=0){
        librarianService.deleteLibByName(name);
        return "delete_success";
        }else
        return "name_not_found";
        }catch (Exception e){
        return "delete_fail";
        }
        }

//6.更改图书罚款的金额
@RequestMapping(value = "/modifyBookFine", method = RequestMethod.POST)
@ResponseBody
public String modifyBookFine(
@RequestParam("book_fine_value") double fine,
@RequestParam("timestamp") String timestamp) {
        try{
        ruleService.addRule(fine,-1.0,-1.0,timestamp);
        return "modify_success";
        }catch (Exception ignored){
        return "modify_fail";
        }
        }

//7.获取图书罚款的金额
@RequestMapping(value = "/getBookFine", method = RequestMethod.GET)
@ResponseBody
public JSON getBookFine() {
        try{
        List<Admin_rules> rules= ruleService.getAllInfo();
        JSONArray rulesJSON = new JSONArray();
        JSONObject jsonObject = null;
        for(int i=0;i<ruleService.getAllInfo().size();i++){
        System.out.println(ruleService.getAllInfo().get(i).toString());
        if(rules.get(i).getFine()>0){
        jsonObject=new JSONObject();
        jsonObject.put("book_fine_value", rules.get(i).getFine());
        jsonObject.put("timestamp", rules.get(i).getTimestamp());
        rulesJSON.add(jsonObject);
        }else
        continue;
        }
        return rulesJSON;
        }catch (Exception e){
        return null;
        }
        }

//8.更改图书借阅期限
@RequestMapping(value = "/modifyBookPeriod", method = RequestMethod.POST)
@ResponseBody
public String modifyBookPeriod(
@RequestParam("book_period") double period,
@RequestParam("timestamp") String timestamp) {
        try{
        ruleService.addRule(-1.0,period,-1.0,timestamp);
        return "modify_success";
        }catch (Exception e){
        return "modify_fail";
        }
        }

//9.获取图书借阅期限
@RequestMapping(value = "/getBookPeriod", method = RequestMethod.GET)
@ResponseBody
public JSON getBookPeriod() {
        try{
        List<Admin_rules> rules= ruleService.getAllInfo();
        JSONArray rulesJSON = new JSONArray();
        JSONObject jsonObject = null;
        for(int i=0;i<ruleService.getAllInfo().size();i++){
        System.out.println(ruleService.getAllInfo().get(i).toString());
        if(rules.get(i).getPeriod()>0){
        jsonObject=new JSONObject();
        jsonObject.put("book_period", rules.get(i).getPeriod());
        jsonObject.put("timestamp", rules.get(i).getTimestamp());
        rulesJSON.add(jsonObject);
        }else
        continue;
        }
        return rulesJSON;
        }catch (Exception e){
        return null;
        }
        }

//10.更改读者缴纳金
@RequestMapping(value = "/modifyReaderDeposit", method = RequestMethod.POST)
@ResponseBody
public String modifyReaderDeposit(
@RequestParam("reader_deposit") double deposit,
@RequestParam("timestamp") String timestamp) {
        try{
        ruleService.addRule(-1.0,-1.0,deposit,timestamp);
        return "modify_success";
        }catch (Exception e){
        return "modify_fail";
        }
        }

//11.获取读者缴纳金
@RequestMapping(value = "/getReaderDeposit", method = RequestMethod.GET)
@ResponseBody
public JSON getReaderDeposit() {
        try{
        List<Admin_rules> rules= ruleService.getAllInfo();
        JSONArray rulesJSON = new JSONArray();
        JSONObject jsonObject = null;
        for(int i=0;i<ruleService.getAllInfo().size();i++){
        System.out.println(ruleService.getAllInfo().get(i).toString());
        if(rules.get(i).getDeposit()>0){
        jsonObject=new JSONObject();
        jsonObject.put("reader_deposit", rules.get(i).getDeposit());
        jsonObject.put("timestamp", rules.get(i).getTimestamp());
        rulesJSON.add(jsonObject);
        }else
        continue;
        }
        return rulesJSON;
        }catch (Exception e){
        return null;
        }
        }
        }