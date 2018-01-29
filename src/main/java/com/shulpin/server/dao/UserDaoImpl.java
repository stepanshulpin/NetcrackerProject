package com.shulpin.server.dao;

import com.shulpin.shared.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public void saveUser(User user) {
        persist(user);
    }

    @Override
    public User findByUserName(String userName) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("userName",userName));
        return (User) criteria.uniqueResult();
    }
}
