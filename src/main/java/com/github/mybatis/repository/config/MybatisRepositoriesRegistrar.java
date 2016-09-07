package com.github.mybatis.repository.config;

import org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

import java.lang.annotation.Annotation;

/**
 * @author jarvis@caomeitu.com
 * @date 15/9/8
 */
class MybatisRepositoriesRegistrar extends RepositoryBeanDefinitionRegistrarSupport {

    @Override
    protected Class<? extends Annotation> getAnnotation() {
        return EnableMybatisRepositories.class;
    }

    @Override
    protected RepositoryConfigurationExtension getExtension() {
        return new MybatisRepositoryConfigExtension();
    }

}