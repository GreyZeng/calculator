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

    public static void main(String[] args) throws Exception {
        String a = "{\n" +
                "  \"config\": {\n" +
                "    \"numberOfExpression\": 30,\n" +
                "    \"range\": 30,\n" +
                "    \"hasFraction\": false,\n" +
                "    \"hasMultipleAndDivide\": true,\n" +
                "    \"hasParentheses\": true,\n" +
                "    \"hasNegative\": false,\n" +
                "    \"answer\": false,\n" +
                "    \"maxNumberOfOperation\": 3\n" +
                "  },\n" +
                "  \"expressions\": {\n" +
                "    \"3 × ( 3 + 3 )\": \"18\",\n" +
                "    \"( 3 - 1 )\": \"2\"\n" +
                "  }\n" +
                "}\n";
        System.out.println(getCheckedInfo(a));
    }

}
