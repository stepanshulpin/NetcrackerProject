package com.shulpin.server.dao;

import com.shulpin.shared.model.Selector;
import com.shulpin.shared.model.UserInfo;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
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
    public List<String> getCities() {
        Criteria criteria = getSession().createCriteria(UserInfo.class);
        criteria.setProjection(Projections.property("city"));
        criteria.setProjection(Projections.groupProperty("city"));
        return criteria.list();
    }

    @Override
    public List<UserInfo> findAllUsersWithoutUsername(String username, Selector selector) {
        Criteria criteria = getSession().createCriteria(UserInfo.class);
        criteria.add(Restrictions.ne("name",username));
        if(!selector.getGender().equals("")){
            criteria.add(Restrictions.eq("gender", selector.getGender()));
        }
        criteria.add(Restrictions.between("age", selector.getMinAge(), selector.getMaxAge()));

        if(!selector.getCity().equals("")){
            criteria.add(Restrictions.eq("city", selector.getCity()));
        }


        criteria.addOrder(Order.asc(selector.getSortBy()));
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
