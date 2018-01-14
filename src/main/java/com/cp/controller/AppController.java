package com.cp.controller;


import com.cp.models.Config;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;


@Controller
public class AppController {
    @RequestMapping("/")
    @ResponseBody
    String config(Config config) {
        System.out.println(config);
        return "Hello";
    }
}
