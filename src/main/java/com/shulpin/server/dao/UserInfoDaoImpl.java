package com.shulpin.server.dao;

import com.shulpin.shared.model.UserInfo;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userInfoDao")
public class UserInfoDaoImpl extends AbstractDao implements UserInfoDao {
    @Override
    public void saveUserInfo(UserInfo userInfo) {
        persist(userInfo);
    }

    @Override
    public UserInfo getUserInfoByName(String userName) {
        Criteria criteria = getSession().createCriteria(UserInfo.class);
        criteria.add(Restrictions.eq("name",userName));
        return (UserInfo) criteria.uniqueResult();
    }

    @Override
    public List<UserInfo> findAllUsersWithoutUsername(String username) {
        Criteria criteria = getSession().createCriteria(UserInfo.class);
        criteria.add(Restrictions.ne("name",username));
        return criteria.list();
    }

    @Override
    public UserInfo getUserInfoById(Long id) {
        Criteria criteria = getSession().createCriteria(UserInfo.class);
        criteria.add(Restrictions.eq("id",id));
        return (UserInfo) criteria.uniqueResult();
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        getSession().update(userInfo);
    }

    @Override
    public UserInfo whereNotImage() {
        Criteria criteria = getSession().createCriteria(UserInfo.class);
        criteria.add(Restrictions.isNull("image"));
        return (UserInfo) criteria.uniqueResult();
    }
}
