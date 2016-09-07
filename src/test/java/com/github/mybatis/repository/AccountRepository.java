package com.github.mybatis.repository;

import com.github.mybatis.domains.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Bogle on 2016/9/7.
 */
@Mapper
public interface AccountRepository {

    Account selectByPrimaryKey(Integer id);
}
