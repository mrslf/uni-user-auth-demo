package com.coocap.uni.sso.server.controller;


import com.alibaba.fastjson.JSONObject;
import com.coocap.uni.sso.server.common.Constants;
import com.coocap.uni.sso.server.entity.TestUser;
import com.coocap.uni.sso.server.service.TestUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;


@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private TestUserService testUserService;

    /**
     * 登录页
     * @param redirectUrl
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String loginIndex(String redirectUrl, Model model, HttpServletRequest request){

        if (StringUtils.isNotBlank(redirectUrl)){
            model.addAttribute("redirectUrl", redirectUrl);
        }

        return "login";

    }

    /**
     * 登录提交
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(String username, String password, HttpServletRequest request){

        JSONObject result = new JSONObject();

        TestUser testUser = testUserService.selectByUsernameAndPassword(username, password);

        if (testUser != null){
            String token = UUID.randomUUID().toString().replaceAll("-","");
            testUser.setToken(token);
            testUser.setTokenExpire(DateUtils.addDays(new Date(), 7));
            testUserService.updateByPrimaryKey(testUser);
            request.getSession().setAttribute(Constants.LOGIN_FLAG, true);
            result.put("code", 200);
            result.put("token", token);
        } else {
            result.put("code", 400);
        }

        return result;
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject verify(String token, HttpServletRequest request){

        JSONObject result = new JSONObject();
        Object object = request.getSession().getAttribute(Constants.LOGIN_FLAG);

        if (StringUtils.isBlank(token)){
            result.put("code", 400);
        } else {
            TestUser testUser = testUserService.selectByToken(token);
            if (testUser == null || testUser.getTokenExpire().before(new Date()) || object == null || !(boolean)object){
                result.put("code", 400);
            }else {
                result.put("code", 400);
            }
        }
        return result;
    }

}
