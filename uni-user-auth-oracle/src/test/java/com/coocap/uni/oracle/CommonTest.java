package com.coocap.uni.oracle;

import com.coocap.uni.oracle.entity.TestCycleTransaction;
import com.coocap.uni.oracle.mapper.TestCycleTransactionMapper;
import com.coocap.uni.oracle.service.TestCycleTransactionService;
import com.coocap.uni.oracle.service.TestUserService;
import com.coocap.uni.oracle.service.TestUserService2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OracleServieApplication.class)
public class CommonTest {

    @Autowired
    private TestCycleTransactionMapper testCycleTransactionMapper;

    @Autowired
    private TestCycleTransactionService testCycleTransactionService;

    @Autowired
    private TestUserService testUserService;

    @Autowired
    private TestUserService2 testUserService2;

    @Test
    public void testDataSourceInsert(){

        for (int i = 1; i < 201; i++) {

            TestCycleTransaction testCycleTransaction = new TestCycleTransaction();
            testCycleTransaction.setCreateTime(new Date());
            testCycleTransaction.setUpdateTime(new Date());

            if (i % 2 == 0){

                testCycleTransaction.setName("张三" + i);
                testCycleTransaction.setAge(i);
                testCycleTransaction.setSex(1);
                testCycleTransaction.setStatus(1);

            } else {

                testCycleTransaction.setName("李四" + i);
                testCycleTransaction.setAge(i);
                testCycleTransaction.setSex(0);
                testCycleTransaction.setStatus(0);

            }
            testCycleTransactionMapper.insert(testCycleTransaction);
        }

    }


    @Test
    public void testDataSourceSelect(){

        List<TestCycleTransaction> testCycleTransactions = testCycleTransactionMapper.selectAll();
        for (TestCycleTransaction testCycleTransaction : testCycleTransactions) {
            System.out.println(testCycleTransaction.toString());
        }

    }

    @Test
    public void testDataSourceUpdate(){

        List<TestCycleTransaction> testCycleTransactions = testCycleTransactionMapper.selectPageByStatus(1, 10);
        for (TestCycleTransaction testCycleTransaction : testCycleTransactions) {
            testCycleTransaction.setUpdateTime(new Date());
            testCycleTransactionMapper.update(testCycleTransaction);
        }

    }

    @Test
    public void testDataSourceBatch(){

        int pageSize = 10;
        int count = testCycleTransactionMapper.selectCountByStatus();

        for (int i = 1; i <= count / (count/pageSize) + 1; i++) {
            List<TestCycleTransaction> testCycleTransactions = testCycleTransactionMapper.selectPageByStatus(i, pageSize);

            for (TestCycleTransaction testCycleTransaction : testCycleTransactions) {
                System.out.println("更新前：" + testCycleTransaction.toString());
                testCycleTransaction.setStatus(1);
                testCycleTransaction.setUpdateTime(new Date());
                System.out.println("更新后：" + testCycleTransaction.toString());

                testCycleTransactionMapper.update(testCycleTransaction);
            }
        }
    }

    @Test
    public void testDataSourceBatch2(){

        int pageSize = 10;
        int count = testCycleTransactionMapper.selectCountByStatus();

        for (int i = 1; i <= count / (count/pageSize) + 1; i++) {
            List<TestCycleTransaction> testCycleTransactions = testCycleTransactionMapper.selectPageByStatus(1, pageSize);

            for (TestCycleTransaction testCycleTransaction : testCycleTransactions) {
                System.out.println("更新前：" + testCycleTransaction.toString());
                testCycleTransaction.setStatus(1);
                testCycleTransaction.setUpdateTime(new Date());
                System.out.println("更新后：" + testCycleTransaction.toString());

                testCycleTransactionMapper.update(testCycleTransaction);
            }
        }
    }

    @Test
    public void testTransactionBatch(){
        testCycleTransactionService.batchHandle();
    }

    @Test
    public void testTransaction(){
        testCycleTransactionService.testTransaction();
    }

    @Test
    public void testCycle(){

        testCycleTransactionService.testCycle();

    }

    @Test
    public void testUpdateList(){

        List<TestCycleTransaction> testCycleTransactions = testCycleTransactionMapper.selectAll();


        testCycleTransactionMapper.updateList(testCycleTransactions);

    }

    @Test
    public void testMethodTransaction(){

        testUserService2.update();

    }

}
