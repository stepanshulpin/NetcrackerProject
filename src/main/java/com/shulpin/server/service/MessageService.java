package com.shulpin.server.service;

import com.shulpin.shared.model.Message;

import java.util.List;

public interface MessageService {

    void save(Message message);

    List<Message> getAllFrom(Long id);

    List<Message> getAllTo(Long id);

    void delete(Message message);
}
