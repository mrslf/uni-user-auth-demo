package com.coocap.uni.oracle.service.impl;

import com.coocap.uni.oracle.entity.TestUser;
import com.coocap.uni.oracle.mapper.TestUserMapper;
import com.coocap.uni.oracle.service.TestUserService;
import com.coocap.uni.oracle.service.TestUserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service

public class TestUserService2Impl implements TestUserService2 {

    @Autowired
    private TestUserMapper testUserMapper;

    @Autowired
    private TestUserService testUserService;

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void update3() {
        TestUser testUser = new TestUser();
        testUser.setId(3);
        testUser.setUsername("username3");
        testUser.setPassword("password3");
        testUser.setCreateTime(new Date());

        testUserMapper.update(testUser);

        System.out.println("service update3执行完成");

        throw new NumberFormatException("update1随便抛的一个异常");
    }
}
