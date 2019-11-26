package com.whitenight.gate.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GateLogin {

    @RequestMapping("/user/info")
    public Map<String, Object> login(){
        Map<String, Object> result = new HashMap<>();
        result.put("name", "zhangsan");
        System.out.print(result);
        return result;
    }

}
