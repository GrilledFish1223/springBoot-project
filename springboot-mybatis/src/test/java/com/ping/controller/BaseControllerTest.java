package com.ping.controller;

import com.ping.basic.BaseTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Controller测试基类
 *
 * @param <T>
 */
@WebAppConfiguration
@AutoConfigureMockMvc
public class BaseControllerTest<T> extends BaseTest {
    private MockMvc mvc;

    public MockMvc getMvc() {
        return mvc;
    }

    public void setMvc(MockMvc mvc) {
        this.mvc = mvc;
    }

    public void createMockMvc(T t) {
        mvc = MockMvcBuilders.standaloneSetup(t).build();
    }

    /**
     * get请求
     *
     * @param url 请求路径
     * @return result
     * @throws Exception
     */
    public ControllerTestResult get(String url) throws Exception {
        MvcResult mvcResult = mvc.perform((MockMvcRequestBuilders.get(url))
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        ControllerTestResult result = new ControllerTestResult();
        result.setStatus(mvcResult.getResponse().getStatus());
        result.setContent(mvcResult.getResponse().getContentAsString());
        return result;
    }

    /**
     * post 请求
     *
     * @param url       请求路径
     * @param jsonValue json对象
     * @return result
     * @throws Exception
     */
    public ControllerTestResult post(String url, String jsonValue) throws Exception {
        MvcResult mvcResult = mvc.perform((MockMvcRequestBuilders.post(url))
                .content(jsonValue).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        ControllerTestResult result = new ControllerTestResult();
        result.setStatus(mvcResult.getResponse().getStatus());
        result.setContent(mvcResult.getResponse().getContentAsString());
        return result;
    }

    /**
     * put 请求
     *
     * @param url       请求路径
     * @param jsonValue json
     * @return result
     * @throws Exception
     */
    public ControllerTestResult put(String url, String jsonValue) throws Exception {
        MvcResult mvcResult = mvc.perform((MockMvcRequestBuilders.put(url))
                .content(jsonValue).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        ControllerTestResult result = new ControllerTestResult();
        result.setStatus(mvcResult.getResponse().getStatus());
        result.setContent(mvcResult.getResponse().getContentAsString());
        return result;
    }

    /**
     * delete 请求
     *
     * @param url 请求路径
     * @return result
     * @throws Exception
     */
    public ControllerTestResult delete(String url) throws Exception {
        MvcResult mvcResult = mvc.perform((MockMvcRequestBuilders.delete(url))
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        ControllerTestResult result = new ControllerTestResult();
        result.setStatus(mvcResult.getResponse().getStatus());
        result.setContent(mvcResult.getResponse().getContentAsString());
        return result;
    }


}
