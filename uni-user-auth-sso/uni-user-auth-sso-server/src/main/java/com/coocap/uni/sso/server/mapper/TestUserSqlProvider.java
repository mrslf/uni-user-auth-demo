package com.coocap.uni.sso.server.mapper;

import com.coocap.uni.sso.server.entity.TestUser;
import org.apache.ibatis.jdbc.SQL;

public class TestUserSqlProvider {

    public String insertSelective(TestUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("TEST_USER");
        
        if (record.getId() != null) {
            sql.VALUES("ID", "#{id,jdbcType=DECIMAL}");
        }
        
        if (record.getUsername() != null) {
            sql.VALUES("USERNAME", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("PASSWORD", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getToken() != null) {
            sql.VALUES("TOKEN", "#{token,jdbcType=VARCHAR}");
        }
        
        if (record.getTokenExpire() != null) {
            sql.VALUES("TOKEN_EXPIRE", "#{tokenExpire,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TestUser record) {
        SQL sql = new SQL();
        sql.UPDATE("TEST_USER");
        
        if (record.getUsername() != null) {
            sql.SET("USERNAME = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("PASSWORD = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getToken() != null) {
            sql.SET("TOKEN = #{token,jdbcType=VARCHAR}");
        }
        
        if (record.getTokenExpire() != null) {
            sql.SET("TOKEN_EXPIRE = #{tokenExpire,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=DECIMAL}");
        
        return sql.toString();
    }
}