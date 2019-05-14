/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springbootswagger.controller;

import com.ping.springbootswagger.config.BaseResult;
import com.ping.springbootswagger.model.Message;
import com.ping.springbootswagger.repository.MessageRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * value           url 的路径值
 * tags            如果设置这个值， value 的值会被覆盖
 * description     对 API 资源的描述
 * produces        For example, "application/json, application/xml"
 * consumes        For example, "application/json, application/xml"
 * protocols       Possible values: http, https, ws, wss
 * authorizations  ⾼级特性认证时配置
 * hidden          配置为 true 将在⽂档中隐藏
 *
 * </p>
 *
 * @version $Id MessageController.java, v 1.0 2019-05-05 20:55 zsp $$
 * @author: zhangsp
 */
@Api(value = "消息", description = "消息操作 API", position = 100, protocols = "http")
@RestController
@RequestMapping("/")
public class MessageController {

    @Resource
    private MessageRepository messageRepository;

    @ApiOperation(
            value = "消息列表",
            notes = "完整的消息内容列表",
            produces = "application/json, application/xml",
            consumes = "application/json, application/xml",
            response = List.class)
    @GetMapping(value = "messages")
    public List<Message> list() {
        return this.messageRepository.findAll();
    }

    @ApiOperation(
            value = "添加消息",
            notes = "根据参数创建消息"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "消息 ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "text", value = "正文", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "summary", value = "摘要", dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "message")
    public Message create(Message message) {
        System.out.println("message====" + message.toString());
        message = this.messageRepository.save(message);
        return message;
    }

    @ApiOperation(
            value = "修改消息",
            notes = "根据参数修改消息"
    )
    @PutMapping(value = "message")
    @ApiResponses({
            @ApiResponse(code = 100, message = "请求参数有误"),
            @ApiResponse(code = 101, message = "未授权"),
            @ApiResponse(code = 103, message = "禁止访问"),
            @ApiResponse(code = 104, message = "请求路径不存在"),
            @ApiResponse(code = 200, message = "服务器内部错误")
    })
    public Message modify(Message message) {
        return this.messageRepository.update(message);
    }

    @PatchMapping(value = "/message/text")
    public BaseResult<Message> patch(Message message) {
        Message messageResult = this.messageRepository.updateText(message);
        return BaseResult.successWithData(messageResult);
    }

    @GetMapping(value = "message/{id}")
    public Message get(@PathVariable Long id) {
        return this.messageRepository.findMessage(id);
    }

    @DeleteMapping(value = "message/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.messageRepository.deleteMessage(id);
    }


}
