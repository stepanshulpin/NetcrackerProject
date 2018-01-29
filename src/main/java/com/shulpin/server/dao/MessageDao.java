package com.shulpin.server.dao;

import com.shulpin.shared.model.Message;

import java.util.List;

public interface MessageDao {

    void save(Message message);

    List<Message> getAllFrom(Long id);

    List<Message> getAllTo(Long id);

    void deleteMsg(Message message);
}
