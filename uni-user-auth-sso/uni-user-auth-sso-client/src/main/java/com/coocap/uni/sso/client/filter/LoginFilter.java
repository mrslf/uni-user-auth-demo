package com.coocap.uni.sso.client.filter;


import com.alibaba.fastjson.JSONObject;
import com.coocap.uni.sso.client.common.Constants;
import com.coocap.uni.sso.client.common.HttpUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter implements Filter {

    //sso服务器登录地址
    private String ssoServerLoginUrl;

    //sso服务器token校验地址
    private String ssoServerVerifyUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        System.out.println("客户端jsessionId:" + req.getSession().getId());

        //访问地址
        String redirectUrl = req.getRequestURL().toString();
        Object object = session.getAttribute(Constants.LOGIN_FLAG);

        if (object != null && (boolean) object) {
            chain.doFilter(request, response);
            return;
        }

        String token = req.getParameter("token");

        if (token != null) {
            // 去sso认证中心校验token
            boolean verifyResult = this.verify(ssoServerVerifyUrl, token);
            if (!verifyResult) {
                res.sendRedirect(ssoServerLoginUrl + "?redirectUrl=" + redirectUrl);
                return;
            }
            session.setAttribute(Constants.LOGIN_FLAG, true);
            chain.doFilter(request, response);
            return;
        }

        //跳转至sso认证中心
        res.sendRedirect(ssoServerLoginUrl + "?redirectUrl=" + redirectUrl);

    }

    /**
     * 校验token
     * @param ssoServerVerifyUrl
     * @param token
     * @return
     */
    private boolean verify(String ssoServerVerifyUrl, String token) throws UnsupportedEncodingException {

        Map<String, String> paramMap = new HashMap<>(2);
        paramMap.put("token", token);
        String result = HttpUtil.httpPostRequest(ssoServerVerifyUrl, paramMap);
        if ("200".equals(JSONObject.parseObject(result).getString("code"))){
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void destroy() {

    }



    public String getSsoServerLoginUrl() {
        return ssoServerLoginUrl;
    }

    public void setSsoServerLoginUrl(String ssoServerLoginUrl) {
        this.ssoServerLoginUrl = ssoServerLoginUrl;
    }

    public String getSsoServerVerifyUrl() {
        return ssoServerVerifyUrl;
    }

    public void setSsoServerVerifyUrl(String ssoServerVerifyUrl) {
        this.ssoServerVerifyUrl = ssoServerVerifyUrl;
    }
}
