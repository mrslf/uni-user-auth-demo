package com.coocap.uni.sso.server.filter;


import com.coocap.uni.sso.server.common.Constants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String redirectUrl = req.getParameter("rediectUrl");

        if ((boolean) session.getAttribute(Constants.LOGIN_FLAG)) {

            if (StringUtils.isNotBlank(redirectUrl)){
                res.sendRedirect(redirectUrl);
            } else {
                res.sendRedirect("http://localhost:5400/index");
            }
        } else {
            res.sendRedirect("http://localhost:5400/login/index?redirectUrl=" + redirectUrl);
        }
    }


    @Override
    public void destroy() {

    }



}
