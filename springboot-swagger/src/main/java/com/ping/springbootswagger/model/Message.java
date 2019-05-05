/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springbootswagger.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @version $Id Message.java, v 1.0 2019-05-05 21:16 zsp $$
 * @author: zhangsp
 */
@Data
public class Message {
    private Long id;
    @ApiModelProperty(value = "消息体")
    private String text;
    @ApiModelProperty(value = "消息总结")
    private String summary;
    private Date createDate;

}
