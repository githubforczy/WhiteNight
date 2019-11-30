package com.whitenight.gate.controller;


import com.whitenight.gate.base.ModelResult;
import com.whitenight.gate.base.annotation.WnLog;
import com.whitenight.gate.entity.User;
import com.whitenight.gate.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户信息",description = "用户信息操作API",protocols = "http",basePath = "/user/")
@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${username:czy}")
    private String username;

    @Resource
    private UserService userService;

    @ApiOperation(
        value = "获取用户信息",
        notes = "当前登陆用户的信息",
        produces = "application/json, application/xml",
        consumes = "application/json, application/xml",
        response = ModelResult.class
    )
    @GetMapping("/info")
    @WnLog(level = "debug",value = "获取登陆用户明")
    public ModelResult login(){
        Map<String, Object> result = new HashMap<>();
        result.put("name", username);
        return ModelResult.getSuccess("成功",result);
    }

    @ApiOperation(
        value = "添加用户信息",
        notes = "添加用户信息",
        produces = "application/json, application/xml",
        consumes = "application/json, application/xml",
        response = ModelResult.class
    )
    @PostMapping(value = "/add")
    @ResponseBody
    public ModelResult addUser(@RequestBody User user){
        userService.addUser(user);
        return ModelResult.getSuccess("保存成功");
    }
}
