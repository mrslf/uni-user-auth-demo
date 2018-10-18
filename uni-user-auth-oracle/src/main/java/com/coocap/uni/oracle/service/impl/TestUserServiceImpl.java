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
//@Transactional(rollbackFor = Exception.class)
public class TestUserServiceImpl implements TestUserService {

    @Autowired
    private TestUserMapper testUserMapper;

    @Autowired
    private TestUserService2 testUserService2;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void update1() {

        TestUser testUser = new TestUser();

        testUser.setId(1);
        testUser.setUsername("username1");
        testUser.setPassword("password1");
        testUser.setCreateTime(new Date());

        testUserMapper.update(testUser);

        System.out.println("service update执行完成");
        throw new NumberFormatException("update1随便抛的一个异常");

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void update2() {
        TestUser testUser = new TestUser();

        testUser.setId(2);
        testUser.setUsername("username2");
        testUser.setPassword("password2");
        testUser.setCreateTime(new Date());

        testUserMapper.update(testUser);

        System.out.println("service update2执行完成");

        try {
            testUserService2.update3();
        }catch (Exception e){
            System.out.println("调用其他处理逻辑...update4()");
        }
        System.out.println("done!");
    }
}
