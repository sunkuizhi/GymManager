package com.test.GymManager.controller;

import com.alibaba.fastjson2.JSON;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
@Api(value = "测试",tags = {"测试前后端分离TestController的写法"})
public class TestController {

    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "两个参数接收用户的输入信息",tags = {"获取用户登录信息"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uname",value = "用户名",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "pwd",value = "密码",required = true,paramType = "query",dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "响应给前端的参数及状态信息")
    })
    public String loginUser(String uname,String pwd){
        Map<String, Object> map = new HashMap();
        map.put("status",200);
        map.put("uname",uname);
        //使用阿里巴巴的fastjson技术,把对象转成String类型的json数据
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }
}
