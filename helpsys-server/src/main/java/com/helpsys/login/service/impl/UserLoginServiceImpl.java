package com.helpsys.login.service.impl;

import com.helpsys.login.pojo.AdminUser;
import com.helpsys.login.service.UserLoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import javax.security.sasl.AuthenticationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Override
    public Map<String, Object> isPuserExistByAccountAndPass(Map map) {
        Map<String, Object> reMap = new HashMap<>();
        String userAccount = (String) map.get("userAccount");
        String userPass = (String) map.get("userPass");
        /*AdminUser user = new AdminUser();
        user.se
                user.set
                        adminDao.get();
        if(!=null){
            user.getUserLoginName

        }*/
        reMap.put("userAccount", "13055765738");
        reMap.put("userName", "张雷");
        return reMap;
    }
}
