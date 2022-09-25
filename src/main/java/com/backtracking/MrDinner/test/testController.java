package com.backtracking.MrDinner.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testController {

    @GetMapping("/api")
    @ResponseBody
    public String testapi(@RequestParam String req){
        System.out.println(req);
        return req+" 성공";
    }
}
