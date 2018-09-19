package com.coocap.uni.oracle.mapper;

import com.coocap.uni.oracle.entity.TestUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;

public interface TestUserMapper {
    @Insert({
        "insert into TEST_USER (ID, USERNAME, ",
        "PASSWORD, CREATE_TIME)",
        "values (#{id,jdbcType=VARCHAR}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(TestUser record);

    @InsertProvider(type=TestUserSqlProvider.class, method="insertSelective")
    int insertSelective(TestUser record);
}