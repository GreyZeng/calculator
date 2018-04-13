package com.hui.calculator.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.hui.calculator.core.Checker.errorResult;
import static com.hui.calculator.core.Checker.getCheckedInfo;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author zenghui
 */
@RestController
public class CheckController {


    @RequestMapping(value = "/checking", method = POST)
    @ResponseBody
    public String checking(@RequestParam(value = "answer") String answer) {
        try {
            return getCheckedInfo(answer);
        } catch (Exception e) {
            return errorResult(e.getMessage());
        }
    }

}
