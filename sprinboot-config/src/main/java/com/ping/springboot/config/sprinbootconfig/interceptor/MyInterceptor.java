/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springboot.config.sprinbootconfig.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version $Id MyInterceptor.java, v 1.0 2019-05-14 17:13 zsp $$
 * @author: zhangsp
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            LOGGER.debug("request method -> {}", request.getMethod());

            String requestURI = request.getRequestURI();
            if (StringUtils.isBlank(requestURI)) {
                return false;
            }

            String bodyString = new ServletRequestWrapper(request).getBodyString(request);
            LOGGER.debug("Json", bodyString.toString());

            JSONObject jsonObject = JSON.parseObject(bodyString);
            if (jsonObject.containsKey("test")) {
                String o = ""+jsonObject.getJSONObject("test").get("value");
                cleanXss(o.trim());
                return true;
            } else {
                return false;
            }
        }catch (Exception e) {
            LOGGER.error("handle interceptor error, ", e);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public String  cleanXss (String value) {
        value = value.replaceAll("_", "\\\\_");

        return value;
    }
}
