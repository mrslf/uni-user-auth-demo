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

        testUser.setId(7);
        testUser.setUsername("username7");
        testUser.setPassword("password7");
        testUser.setCreateTime(new Date());

        testUserMapper.insert(testUser);

        throw new NumberFormatException("随便抛的一个异常");

    }
}
