package com.man.mandarin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.man.mandarin.entity.Admin;
import com.man.mandarin.entity.Admin_rules;
import com.man.mandarin.entity.Book;
import com.man.mandarin.entity.Librarian;
import com.man.mandarin.service.AdminService;
import com.man.mandarin.service.BookService;
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
    private BookService bookService;
    // 1.登录
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
    //2.添加书籍
    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    @ResponseBody
    public JSON addBook(@RequestParam("book_isbn") String isbn,
                        @RequestParam("book_title") String title,
                        @RequestParam("book_author") String author,
                        @RequestParam("book_publisher") String publisher,
                        @RequestParam("book_price") double price,
                        @RequestParam("book_location") String location,
                        @RequestParam("book_status") int status,
                        @RequestParam("book_barcode") String barcode,
                        @RequestParam("category") String category){
        JsonUtil jsonUtil =new JsonUtil();
        try{
            bookService.addBook(isbn,title,author,publisher,price,location,status,barcode,category);
            return jsonUtil.messagetoJson("success",null);
        }catch (Exception e){
            return jsonUtil.messagetoJson("fail","add_fail");
        }
    }
    //3.删除图书
    @RequestMapping(value = "/deleteBook",method = RequestMethod.POST)
    @ResponseBody
    public JSON deleteBook(@RequestParam("book_id") int id){
        JsonUtil jsonUtil=new JsonUtil();
        try{
            List<Book> books=bookService.queryById(id);
            if (books.size()!=0){
                bookService.deleteBookById(id);
                return jsonUtil.messagetoJson("success",null);
            }else
                return jsonUtil.messagetoJson("fail","name_not_found");
        }catch (Exception e){
            return jsonUtil.messagetoJson("fail","delete_fail");
        }
    }

    //4.图书管理员修改图书信息
    @RequestMapping(value = "/modifyBookInfo",method = RequestMethod.POST)
    @ResponseBody
public JSON modifyBookInfo(@RequestParam("book_id") int id,
                           @RequestParam("book_isbn") String isbn,
                           @RequestParam("book_title") String title,
                           @RequestParam("book_author") String author,
                           @RequestParam("book_publisher") String publisher,
                           @RequestParam("book_price") double price,
                           @RequestParam("book_location") String location,
                           @RequestParam("book_status") int status,
                           @RequestParam("book_barcode") String barcode,
                           @RequestParam("category") String category){
        JsonUtil jsonUtil=new JsonUtil();
        try{
            List<Book> books=bookService.queryById(id);
            bookService.addBook(isbn,title,author,publisher,price,location,status,barcode,category);
            return jsonUtil.messagetoJson("success",null);
        }catch (Exception e){
            return jsonUtil.messagetoJson("fail","edit_fail");
        }
    }
    //5.通过图书名称搜索书籍
    @RequestMapping(value = "/searchBookByTitle",method=RequestMethod.POST)
    @ResponseBody
    public JSON searchBookByTitle(@RequestParam("book_title") String title){
        List<Book> books=bookService.queryByTitle(title);
        JsonUtil jsonUtil=new JsonUtil();
        try{
            if (books.size()!=0){
                return jsonUtil.booktoJson("success",books,null);
            }else
                return jsonUtil.booktoJson("fail",books,"title_not_found");
        }catch (Exception e){
            return jsonUtil.booktoJson("fail",books,"search_fail");
        }
    }
    //6.通过图书isbn搜索书籍
    @RequestMapping(value = "/searchBookByISBN",method=RequestMethod.POST)
    @ResponseBody
    public JSON searchBookByISBN(@RequestParam("book_isbn") String isbn){
        List<Book> books=bookService.queryByIsbn(isbn);
        JsonUtil jsonUtil=new JsonUtil();
        try{
            if (books.size()!=0){
                return jsonUtil.booktoJson("success",books,null);
            }else
                return jsonUtil.booktoJson("fail",books,"isbn_not_found");
        }catch (Exception e){
            return jsonUtil.booktoJson("fail",books,"search_fail");
        }
    }

}
