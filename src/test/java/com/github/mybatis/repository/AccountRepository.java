package com.github.mybatis.repository;

import com.github.mybatis.domains.Account;
import com.github.mybatis.repository.support.MybatisRepository;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Bogle on 2016/9/7.
 */
@Mapper
public interface AccountRepository extends MybatisRepository<Account, Long> {

    Account selectByPrimaryKey(Integer id);

    Page<Account> findAll(Pageable pageable);
}
