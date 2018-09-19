package com.coocap.uni.oracle.mapper;

import com.coocap.uni.oracle.entity.TestCycleTransaction;
import org.apache.ibatis.jdbc.SQL;

public class TestCycleTransactionSqlProvider {

    public String insertSelective(TestCycleTransaction record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("TEST_CYCLE_TRANSACTION");
        
        if (record.getId() != null) {
            sql.VALUES("ID", "#{id,jdbcType=DECIMAL}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("NAME", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            sql.VALUES("SEX", "#{sex,jdbcType=DECIMAL}");
        }
        
        if (record.getAge() != null) {
            sql.VALUES("AGE", "#{age,jdbcType=DECIMAL}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("STATUS", "#{status,jdbcType=DECIMAL}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }
}