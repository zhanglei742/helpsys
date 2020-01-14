package com.helpsys.login.service.impl;

import com.helpsys.login.service.UserLoginService;
import org.springframework.beans.BeanUtils;

import javax.security.auth.Subject;
import javax.security.sasl.AuthenticationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UserLoginServiceImpl implements UserLoginService {
    @Override
    public Map<String, Object> isPuserExistByAccountAndPass(Map map) {
        Map<String, Object> reMap = new HashMap<>();
        String userAccount = (String) map.get("userAccount");
        String userPass = (String) map.get("userPass");

        return reMap;
    }
}
