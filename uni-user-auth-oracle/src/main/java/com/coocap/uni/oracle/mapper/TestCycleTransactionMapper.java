package com.coocap.uni.oracle.mapper;

import com.coocap.uni.oracle.entity.TestCycleTransaction;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TestCycleTransactionMapper {
    @Insert({
        "insert into TEST_CYCLE_TRANSACTION (ID, NAME, ",
        "SEX, AGE, STATUS, ",
        "CREATE_TIME, UPDATE_TIME)",
        "values (#{id,jdbcType=DECIMAL}, #{name,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=DECIMAL}, #{age,jdbcType=DECIMAL}, #{status,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT SEQ_TEST_CYCLE_TRANSACTION.nextval AS ID FROM DUAL", keyProperty="id", before=true, resultType=Integer.class)
    int insert(TestCycleTransaction record);

    @InsertProvider(type=TestCycleTransactionSqlProvider.class, method="insertSelective")
    int insertSelective(TestCycleTransaction record);

    @Select("select * from TEST_CYCLE_TRANSACTION")
    List<TestCycleTransaction> selectAll();

    @Select("select * from(select rownum rm, t.*  from TEST_CYCLE_TRANSACTION t where t.STATUS = 0) tm where rm > #{pageSize} * ( #{pageNo} - 1 ) and rm <= #{pageSize} * #{pageNo}")
    List<TestCycleTransaction> selectPageByStatus(@Param("pageNo") int pageNo, @Param("pageSize")int pageSize);

    @Select("select count(1) from TEST_CYCLE_TRANSACTION where STATUS = 0")
    int selectCountByStatus();

    @Update({"update TEST_CYCLE_TRANSACTION set NAME = #{name,jdbcType=VARCHAR}, SEX = #{sex,jdbcType=DECIMAL}, AGE = #{age,jdbcType=DECIMAL}, STATUS = #{status,jdbcType=DECIMAL}, UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} where ID = #{id,jdbcType=DECIMAL}"})
    int update(TestCycleTransaction record);

    @Select({"select * from TEST_CYCLE_TRANSACTION where status=0 and rownum <= 10"})
    List<TestCycleTransaction> selectBatch();
}