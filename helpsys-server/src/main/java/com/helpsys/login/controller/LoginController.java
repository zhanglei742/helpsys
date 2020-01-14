package com.helpsys.login.controller;

import com.helpsys.login.service.UserLoginService;
import com.helpsys.login.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Msg perLogin(@RequestBody Map<String, String> reqMap, HttpServletRequest request) {
        if (reqMap.get("userAccount").equals("") || reqMap.get("userPass").equals("")) {
            return Msg.fail().add("msg", "账号或密码参数错误!");
        } else {
            Map<String, Object> map = userLoginService.isPuserExistByAccountAndPass(reqMap);
            if (map == null) {
                return Msg.fail().add("msg", "账号已删除或无效&&用户名或密码错误,请再次确认！");
            } else {
                return Msg.success().add("user", map);
            }
        }


    }


}
