package com.coocap.uni.oracle.mapper;

import com.coocap.uni.oracle.entity.TestUser;
import org.apache.ibatis.jdbc.SQL;

public class TestUserSqlProvider {

    public String insertSelective(TestUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("TEST_USER");
        
        if (record.getId() != null) {
            sql.VALUES("ID", "#{id,jdbcType=VARCHAR}");
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
        
        return sql.toString();
    }
}