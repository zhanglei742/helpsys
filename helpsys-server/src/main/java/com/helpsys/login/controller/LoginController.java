package com.helpsys.login.controller;

import com.helpsys.login.service.UserLoginService;
import com.helpsys.login.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@CrossOrigin
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object perLogin(String userName, String passWord, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        Map<String,Object> result = new HashMap<String,Object>();
        System.out.println("登录验证！！"+userName+passWord);
//        if (username.equals("") || password.equals("")) {
//            return Msg.fail().add("msg", "账号或密码参数错误!");
//        } else {
//            Map<String, Object> result = userLoginService.isPuserExistByAccountAndPass(para);
//            if (result == null) {
//                return Msg.fail().add("msg", "账号已删除或无效&&用户名或密码错误,请再次确认！");
//            } else {
//                return Msg.success().add("user", result);
//            }
//        }
        result.put("code",200);
        return  result;

    }


}
