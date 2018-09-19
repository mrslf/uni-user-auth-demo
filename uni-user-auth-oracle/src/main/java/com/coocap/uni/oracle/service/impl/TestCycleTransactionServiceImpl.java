package com.coocap.uni.oracle.service.impl;

import com.coocap.uni.oracle.entity.TestCycleTransaction;
import com.coocap.uni.oracle.mapper.TestCycleTransactionMapper;
import com.coocap.uni.oracle.service.TestCycleTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class TestCycleTransactionServiceImpl implements TestCycleTransactionService {

    @Autowired
    private TestCycleTransactionMapper testCycleTransactionMapper;

    @Autowired
    private TestUserService testUserService;

    @Override
    @Transactional
    public void batchHandle() {

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

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update() {

        List<TestCycleTransaction> testCycleTransactions = testCycleTransactionMapper.selectAll();

        TestCycleTransaction testCycleTransaction = testCycleTransactions.get(0);

        testCycleTransaction.setUpdateTime(new Date());

        testCycleTransactionMapper.update(testCycleTransaction);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testTransaction() {
        update();
        testUserService.insert();
    }
}
