package com.github.mybatis.repository;

import com.github.mybatis.domains.Account;
import com.github.mybatis.repository.support.MybatisRepository;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Bogle on 2016/9/7.
 */
@Mapper
public interface AccountRepository extends MybatisRepository<Account, Long> {

    Account selectByPrimaryKey(Integer id);
}
