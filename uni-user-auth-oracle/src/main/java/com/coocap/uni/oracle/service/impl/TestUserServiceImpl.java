package com.coocap.uni.oracle.service.impl;

import com.coocap.uni.oracle.entity.TestUser;
import com.coocap.uni.oracle.mapper.TestUserMapper;
import com.coocap.uni.oracle.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class TestUserServiceImpl implements TestUserService {

    @Autowired
    private TestUserMapper testUserMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert() {

        TestUser testUser = new TestUser();

        testUser.setId(1);
        testUser.setUsername("username");
        testUser.setPassword("password");
        testUser.setCreateTime(new Date());

        testUserMapper.insert(testUser);

    }
}
