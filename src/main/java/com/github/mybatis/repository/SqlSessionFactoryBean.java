package com.github.mybatis.repository;

import com.github.mybatis.repository.config.EnableMybatisRepositories;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Properties;

/**
 * Created by Bogle on 2016/9/7.
 */
public class SqlSessionFactoryBean extends org.mybatis.spring.SqlSessionFactoryBean implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private EnableMybatisRepositories mybatisRepositories;


    @Override
    public void afterPropertiesSet() throws Exception {

        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties p = new Properties();
        p.setProperty("SQL Server", "sqlserver");
        p.setProperty("Oracle", "oracle");
        p.setProperty("DB2", "db2");
        p.setProperty("MySQL", "mysql");
        p.setProperty("H2", "h2");
        databaseIdProvider.setProperties(p);
        setDatabaseIdProvider(databaseIdProvider);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
