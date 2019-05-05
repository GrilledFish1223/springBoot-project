/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springbootswagger.repository;

import com.ping.springbootswagger.model.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version $Id MessageRepository.java, v 1.0 2019-05-05 21:15 zsp $$
 * @author: zhangsp
 */
public interface MessageRepository {
    List<Message> findAll();

    Message save(Message message);

    Message update(Message message);

    Message updateText(Message message);

    Message findMessage(Long id);

    void deleteMessage(Long id);
}
