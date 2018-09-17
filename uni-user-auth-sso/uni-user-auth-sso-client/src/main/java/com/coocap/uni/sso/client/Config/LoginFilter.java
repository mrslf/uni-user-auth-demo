package com.coocap.uni.sso.client.Config;


import com.alibaba.fastjson.JSONObject;
import com.coocap.uni.sso.client.common.Constants;
import com.coocap.uni.sso.client.common.HttpClientUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        if ((boolean) session.getAttribute(Constants.LOGIN_FLAG)) {
            chain.doFilter(request, response);
            return;
        }

        String token = req.getParameter("token");

        if (token != null) {
            // 去sso认证中心校验token
            boolean verifyResult = this.verify(ssoServerVerifyUrl, token);
            if (!verifyResult) {
                res.sendRedirect(ssoServerLoginUrl);
                return;
            }
            session.setAttribute(Constants.LOGIN_FLAG, true);
            chain.doFilter(request, response);
        }

        //跳转至sso认证中心
        res.sendRedirect(ssoServerLoginUrl);

    }

    /**
     * 校验token
     * @param ssoServerVerifyUrl
     * @param token
     * @return
     */
    private boolean verify(String ssoServerVerifyUrl, String token) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        JSONObject result = HttpClientUtils.httpPost(ssoServerVerifyUrl, jsonObject);
        if ("200".equals(result.getString("code"))){
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
