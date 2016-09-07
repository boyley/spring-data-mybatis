package com.github.mybatis.test;

import com.github.mybatis.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Bogle on 2016/9/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void selectByPrimaryKey() {
        accountRepository.selectByPrimaryKey(1);
    }
}
