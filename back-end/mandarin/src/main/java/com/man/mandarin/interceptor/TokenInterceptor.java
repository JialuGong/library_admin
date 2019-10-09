package com.man.mandarin.interceptor;
import com.alibaba.fastjson.JSONObject;
import com.man.mandarin.entity.Admin;
import com.man.mandarin.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor{
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    if (request.getMethod().equals("OPTIONS")) {
      response.setStatus(HttpServletResponse.SC_OK);
      return true;
    }
    response.setCharacterEncoding("utf-8");
    String token = request.getHeader("admin_token");
    if (token != null) {
      boolean result = TokenUtil.verify(token);
      if (result) {
        System.out.println("Through the interceptor!");
        return true;
      }
    }
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json; charset=utf-8");
    PrintWriter out = null;
    try {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("data", null);
      jsonObject.put("message", "Authentication_failed");
      jsonObject.put("code", 0);
      response.getWriter().append(jsonObject.toString());
      System.out.println("failed to pass the interceptor");
    } catch (Exception e) {
      e.printStackTrace();
      response.sendError(500);
      return false;
    }
    return false;
        }
}
