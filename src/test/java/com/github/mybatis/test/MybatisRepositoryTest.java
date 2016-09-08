package com.github.mybatis.test;

import com.github.mybatis.repository.AccountRepository;
import com.github.mybatis.repository.config.EnableMybatisRepositories;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Bogle on 2016/9/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableMybatisRepositories(
        basePackages = {"com.github.mybatis.repository"}
)
public class MybatisRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findPage() {
        accountRepository.findAll(new PageRequest(1,1));
    }

    @Test
    public void selectByPrimaryKey() {
        accountRepository.selectByPrimaryKey(1);
    }
}
