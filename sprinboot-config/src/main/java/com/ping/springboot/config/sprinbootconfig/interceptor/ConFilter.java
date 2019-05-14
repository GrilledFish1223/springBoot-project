/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springboot.config.sprinbootconfig.interceptor;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @version $Id ConFilter.java, v 1.0 2019-05-14 17:09 zsp $$
 * @author: zhangsp
 */
@WebFilter(filterName = "myFilter", urlPatterns = "/**")
@Component
public class ConFilter implements Filter {
    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ServletRequest requestWrapper = new ServletRequestWrapper(request);
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
