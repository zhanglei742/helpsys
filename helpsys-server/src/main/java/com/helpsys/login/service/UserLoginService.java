package com.helpsys.login.service;

import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface UserLoginService {
    /**
     * 判断用户是否存在
     * @param map
     * @return
     */
     Map<String, Object> isPuserExistByAccountAndPass(Map map);
}
