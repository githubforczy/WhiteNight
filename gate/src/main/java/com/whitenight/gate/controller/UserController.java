package com.whitenight.gate.controller;


import com.whitenight.gate.base.ModelResult;
import com.whitenight.gate.entity.Users;
import com.whitenight.gate.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
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
    public ModelResult login(){
        Map<String, Object> result = new HashMap<>();
        result.put("name", "czys");
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
    public ModelResult addUser(@RequestBody Users users){
        userService.addUser(users);
        return ModelResult.getSuccess("保存成功");
    }
}
