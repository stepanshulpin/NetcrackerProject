package com.shulpin.server.service;


import com.shulpin.server.dao.UserInfoDao;
import com.shulpin.shared.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDao dao;

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        dao.saveUserInfo(userInfo);
    }

    @Override
    public UserInfo getUserInfoByName(String userName) {
        return dao.getUserInfoByName(userName);
    }

    @Override
    public List<UserInfo> findAllUsersWithoutUsername(String username) {
        return dao.findAllUsersWithoutUsername(username);
    }

    @Override
    public UserInfo getUserInfoById(Long id) {
        return dao.getUserInfoById(id);
    }
}
