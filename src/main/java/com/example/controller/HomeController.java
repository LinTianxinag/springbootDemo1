package com.example.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2017/10/24.
 * time:${time}
 */
@RestController
@RequestMapping(value = "/Home")
public class HomeController {
    @RequestMapping(value = "/index")
    public ModelAndView index(){
        System.out.println("req:/Home/indx");
        return new ModelAndView("Index");
    }
    @RequestMapping(value = "/testData")
    @ResponseBody
    public String  testData(){
        System.out.println("req:/Home/testData");
        return "hello spring";
    }
}
