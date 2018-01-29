package com.shulpin.server.dao;

import com.shulpin.shared.model.Message;
import com.shulpin.shared.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("messageDao")
public class MessageDaoImpl extends AbstractDao implements MessageDao {
    @Override
    public void save(Message message) {
        persist(message);
    }

    @Override
    public List<Message> getAllFrom(Long id) {
        Criteria criteria = getSession().createCriteria(Message.class);
        criteria.add(Restrictions.eq("fromId",id));
        return criteria.list();
    }

    @Override
    public List<Message> getAllTo(Long id) {
        Criteria criteria = getSession().createCriteria(Message.class);
        criteria.add(Restrictions.eq("toId",id));
        return criteria.list();
    }

    @Override
    public void deleteMsg(Message message) {
        delete(message);
    }
}
