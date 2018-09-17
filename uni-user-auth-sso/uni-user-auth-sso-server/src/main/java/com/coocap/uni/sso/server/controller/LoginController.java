package com.coocap.uni.sso.server.controller;


import com.alibaba.fastjson.JSONObject;
import com.coocap.uni.sso.server.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/index")
    public String loginIndex(String redirectUrl, Model model){

        logger.info("sso登录页,重定向url:{}", redirectUrl);
        model.addAttribute("redirect_url", redirectUrl);

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(String username, String password, HttpServletRequest request){

        JSONObject result = new JSONObject();

        if ("admin".equals(username) && "123456".equals(password)){
            request.getSession().setAttribute(Constants.LOGIN_FLAG, "true");
            String token = UUID.randomUUID().toString().replaceAll("-","");
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

        if ((boolean)request.getSession().getAttribute(Constants.LOGIN_FLAG)){

            result.put("code", 200);
        } else {
            result.put("code", 400);
        }

        return result;
    }

}
