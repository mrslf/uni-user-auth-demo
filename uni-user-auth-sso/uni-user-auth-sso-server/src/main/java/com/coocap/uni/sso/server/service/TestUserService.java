package com.coocap.uni.sso.server.service;

import com.coocap.uni.sso.server.entity.TestUser;

public interface TestUserService {

    TestUser selectByUsernameAndPassword(String username, String password);

    void updateByPrimaryKey(TestUser testUser);

    TestUser selectByToken(String token);
}
