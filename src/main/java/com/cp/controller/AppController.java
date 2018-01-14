package com.cp.controller;


import com.cp.models.Config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AppController {
    @RequestMapping("/")
    @ResponseBody
    String config(Config config) {
        System.out.println(config);
        return "Hello";
    }
}
