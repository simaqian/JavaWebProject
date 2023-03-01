package com.smq.controller;

import com.alibaba.fastjson.JSON;
import com.smq.pojo.Brand;
import com.smq.pojo.PageBean;
import com.smq.service.BrandService;
import com.smq.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService service = new BrandServiceImpl();

    public  void  selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Brand> brands = service.selectAll();
        String s = JSON.toJSONString(brands);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(s);
    }
    public void add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        BufferedReader br = request.getReader();
        String s = br.readLine();
        int[] brand = JSON.parseObject(s, int[].class);
       service.add(brand);
        response.getWriter().write("success");
    }
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        BufferedReader br = request.getReader();
        String s = br.readLine();
        int[] ids = JSON.parseObject(s, int[].class);
        service.deleteByIds(ids);
        response.getWriter().write("success");
    }
    public void deleteById(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        BufferedReader br = request.getReader();
        String s = br.readLine();
        int id = JSON.parseObject(s, Integer.class);
        service.deleteById(id);
        response.getWriter().write("success");
    }
    public  void  selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        System.out.println(currentPage);
        System.out.println(pageSize);
        PageBean<Brand> pageBean = service.selectByPage(currentPage, pageSize);
        String s = JSON.toJSONString(pageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(s);
    }
    public  void  selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));

        BufferedReader br = req.getReader();
        String s = br.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        System.out.println(brand);

        PageBean<Brand> pageBean = service.selectByPageAndCondition(currentPage, pageSize,brand);
        String string = JSON.toJSONString(pageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(string);
    }
}
