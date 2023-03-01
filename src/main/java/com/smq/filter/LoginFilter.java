//package com.smq.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter("/*")
//public class LoginFilter implements Filter {
//    public void init(FilterConfig config) throws ServletException {
//    }
//
//    public void destroy() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        String[] urls = {"/login.jsp","/register.jsp","/imgs/","/css/","/loginServlet","/registerServlet","/checkCodeServlet"};
//        String s = req.getRequestURL().toString();
//        for(String u: urls){
//            if(s.contains(u)){
//                chain.doFilter(request,response);
//                return;
//            }
//        }
//
//        HttpSession session = req.getSession();
//        Object user = session.getAttribute("user");
//        if(user != null){
//            chain.doFilter(request, response);
//        }else {
//            req.setAttribute("login_msg","请登录");
//            req.getRequestDispatcher("/login.jsp").forward(request,response);
//        }
//
//    }
//}
