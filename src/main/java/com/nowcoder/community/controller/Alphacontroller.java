package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class Alphacontroller {

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot";
    }

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter();){
            writer.write("<h1>Nowcoder<h1>");
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    //GET请求，获取某些数据
    // /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET )
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "1") int limit){
        System.out.println("Current" + current);
        System.out.println("Limit" + limit);
        return "belle and queen";
    }

    // /student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "belle";
    }

    //POST请求，浏览器向服务器提交数据
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 响应HTML数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        //模板需要多少变量，输入多少数据
        mav.addObject("name", "Belle");
        mav.addObject("age", "56");

        //模板的路径和名字
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name", "DLMU");
        model.addAttribute("age", "80");
        return "/demo/view";
    }

    //异步请求，相应JSON数据(当前网页不刷新，悄悄访问服务器返回了局部验证的结果，这个结果不是HTML而是JSON)
    //Java对象返回给浏览器，浏览器把Java对象转换成JSON字符串，然后传给JS对象解析。
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "Belle");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp1 = new HashMap<>();
        emp1.put("name", "Belle");
        emp1.put("age", 23);
        emp1.put("salary", 8000.00);
        list.add(emp1);

        emp1 = new HashMap<>();
        emp1.put("name", "Belle");
        emp1.put("age", 23);
        emp1.put("salary", 8000.00);
        list.add(emp1);

        return list;
    }
}
