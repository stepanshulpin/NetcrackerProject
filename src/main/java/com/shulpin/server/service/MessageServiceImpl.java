package com.shulpin.server.service;


import com.shulpin.server.dao.MessageDao;
import com.shulpin.shared.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao dao;

    @Override
    public void save(Message message) {
        dao.save(message);
    }

    @Override
    public List<Message> getAllFrom(Long id) {
        return dao.getAllFrom(id);
    }

    @Override
    public List<Message> getAllTo(Long id) {
        return dao.getAllTo(id);
    }

    @Override
    public void delete(Message message) {
        dao.deleteMsg(message);
    }
}
