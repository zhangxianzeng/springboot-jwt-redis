//package com.example.token.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.JSONPObject;
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.example.token.annotation.AuthToken;
//import com.example.token.entity.User;
//import com.example.token.kit.ConstantKit;
//import com.example.token.kit.Md5TokenGenerator;
//import com.example.token.kit.TokenGenerator;
//import com.example.token.mapper.UserMapper;
//import com.example.token.model.ResponseTemplate;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import redis.clients.jedis.Jedis;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * @author cailei.lu
// * @description
// * @date 2018/8/3
// */
//
//@RestController
//@Slf4j
//@RequestMapping("/api/v1/")
//public class UserController {
//
//    @Autowired
//    Md5TokenGenerator tokenGenerator;
//
//    @Autowired
//    UserMapper userMapper;
//
//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    @ApiOperation("用户登录接口")
//    public ResponseTemplate login(@RequestBody(required = false) JSONObject userInfo) {
//
//        String username = userInfo.getString("username");
//        String password = userInfo.getString("password");
//
////        User curentUser = new User().selectOne(new EntityWrapper<User>()
////                .eq("username",username)
////                .eq("password",password)
////                .eq("del_flag",0));
//
//        List<User> users = userMapper.selectList(new EntityWrapper<User>()
//                .eq("username", username)
//                .eq("password", password));
//        User currentUser = users.get(0);
//
//        JSONObject result = new JSONObject();
//        if (currentUser != null) {
//
//            Jedis  jedis = new Jedis("172.16.238.150", 6379);
//普遍生成token的方法
//            String token = tokenGenerator.generate(username, password);
//
//            jedis.set(username, token);
//            jedis.expire(username, ConstantKit.TOKEN_EXPIRE_TIME);
//            jedis.set(token, username);
//            jedis.expire(token, ConstantKit.TOKEN_EXPIRE_TIME);
//            Long currentTime = System.currentTimeMillis();
//            jedis.set(token + username, currentTime.toString());
//
//            //用完关闭
//            jedis.close();
//
//            result.put("status", "登录成功");
//            result.put("token", token);
//        } else {
//            result.put("status", "登录失败");
//        }
//
//        return ResponseTemplate.builder()
//                .code(200)
//                .message("登录成功")
//                .data(result)
//                .build();
//
//    }
//
//    @ApiOperation("测试token接口")
//    @RequestMapping(value = "test", method = RequestMethod.GET)
//    @AuthToken
//    public ResponseTemplate test() {
//        List<User> user = new User().selectAll();
//        return ResponseTemplate.builder()
//                .code(200)
//                .message("Success")
//                .data(user)
//                .build();
//    }
//    @ApiOperation("测试")
//    @RequestMapping(value = "test1", method = RequestMethod.GET)
//    public ResponseTemplate test1(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        Jedis  jedis = new Jedis("172.16.238.150", 6379);
//        String username=jedis.get(token);
//        List<User> users = userMapper.selectList(new EntityWrapper<User>()
//                .eq("username", username));
//
//       // User currentUser = users.get(0);
//        return ResponseTemplate.builder()
//                .code(200)
//                .message("Success")
//                .data(users)
//                .build();
//    }
//
//}
