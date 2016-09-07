package com.github.mybatis;

import com.github.mybatis.repository.AccountRepository;
import com.github.mybatis.repository.config.EnableMybatisRepositories;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableMybatisRepositories(
        basePackages = {"com.github.mybatis.repository"}
)
public class SpringDataMybatisApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void selectByPrimaryKey() {
        accountRepository.selectByPrimaryKey(1);
    }

}
