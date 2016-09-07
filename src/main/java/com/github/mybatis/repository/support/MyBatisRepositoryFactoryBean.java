package com.github.mybatis.repository.support;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.Assert;

import java.io.Serializable;

public class MyBatisRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
        extends RepositoryFactoryBeanSupport<T, S, ID> {

    private SqlSessionTemplate sqlSessionTemplate;

    private AuditorAware auditorAware;

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(sqlSessionTemplate, "SqlSessionTemplate must not be null!");
        super.afterPropertiesSet();

    }

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Autowired(required = false)
    public void setAuditorAware(AuditorAware auditorAware) {
        this.auditorAware = auditorAware;
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory() {
        MybatisRepositoryFactory factory = new MybatisRepositoryFactory(sqlSessionTemplate);
        factory.setAuditorAware(auditorAware);
        return factory;
    }


}
