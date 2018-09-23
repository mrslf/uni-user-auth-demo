package com.coocap.uni.sso.server.test;

import com.coocap.uni.sso.server.SsoServerApplication;
import com.coocap.uni.sso.server.entity.TestUser;
import com.coocap.uni.sso.server.service.TestUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SsoServerApplication.class)
public class Test1 {

    @Autowired
    private TestUserService testUserService;

    @Test
    public void test(){
        TestUser testUser = testUserService.selectByUsernameAndPassword("admin", "123456");

        System.out.println(testUser);
    }


}
