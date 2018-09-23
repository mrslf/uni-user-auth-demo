package com.coocap.uni.sso.server.mapper;

import com.coocap.uni.sso.server.entity.TestUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface TestUserMapper {
    @Delete({
        "delete from TEST_USER",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into TEST_USER (ID, USERNAME, ",
        "PASSWORD, CREATE_TIME, ",
        "TOKEN, TOKEN_EXPIRE)",
        "values (#{id,jdbcType=DECIMAL}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{token,jdbcType=VARCHAR}, #{tokenExpire,jdbcType=TIMESTAMP})"
    })
    int insert(TestUser record);

    @InsertProvider(type=TestUserSqlProvider.class, method="insertSelective")
    int insertSelective(TestUser record);

    @Select({
        "select",
        "ID, USERNAME, PASSWORD, CREATE_TIME, TOKEN, TOKEN_EXPIRE",
        "from TEST_USER",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="USERNAME", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="PASSWORD", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="TOKEN", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="TOKEN_EXPIRE", property="tokenExpire", jdbcType=JdbcType.TIMESTAMP)
    })
    TestUser selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "ID, USERNAME, PASSWORD, CREATE_TIME, TOKEN, TOKEN_EXPIRE",
            "from TEST_USER",
            "where USERNAME = #{username,jdbcType=VARCHAR}",
            "and PASSWORD = #{password,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
            @Result(column="USERNAME", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="PASSWORD", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="TOKEN", property="token", jdbcType=JdbcType.VARCHAR),
            @Result(column="TOKEN_EXPIRE", property="tokenExpire", jdbcType=JdbcType.TIMESTAMP)
    })
    TestUser selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select({
            "select",
            "ID, USERNAME, PASSWORD, CREATE_TIME, TOKEN, TOKEN_EXPIRE",
            "from TEST_USER",
            "where TOKEN = #{token,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
            @Result(column="USERNAME", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="PASSWORD", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="TOKEN", property="token", jdbcType=JdbcType.VARCHAR),
            @Result(column="TOKEN_EXPIRE", property="tokenExpire", jdbcType=JdbcType.TIMESTAMP)
    })
    TestUser selectByToken(@Param("token") String token);

    @UpdateProvider(type=TestUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TestUser record);

    @Update({
        "update TEST_USER",
        "set USERNAME = #{username,jdbcType=VARCHAR},",
          "PASSWORD = #{password,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "TOKEN = #{token,jdbcType=VARCHAR},",
          "TOKEN_EXPIRE = #{tokenExpire,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateByPrimaryKey(TestUser record);
}