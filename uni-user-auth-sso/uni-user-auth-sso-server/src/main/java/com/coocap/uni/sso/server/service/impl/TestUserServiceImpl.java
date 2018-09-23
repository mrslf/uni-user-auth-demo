package com.coocap.uni.sso.server.service.impl;

import com.coocap.uni.sso.server.entity.TestUser;
import com.coocap.uni.sso.server.mapper.TestUserMapper;
import com.coocap.uni.sso.server.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestUserServiceImpl implements TestUserService {

    @Autowired
    private TestUserMapper testUserMapper;

    @Override
    public TestUser selectByUsernameAndPassword(String username, String password) {
        return testUserMapper.selectByUsernameAndPassword(username, password);
    }

    @Override
    public void updateByPrimaryKey(TestUser testUser) {
        testUserMapper.updateByPrimaryKey(testUser);
    }

    @Override
    public TestUser selectByToken(String token) {
        return testUserMapper.selectByToken(token);
    }
}
