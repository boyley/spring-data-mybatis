package com.github.mybatis.repository.config;

import com.github.mybatis.repository.support.MyBatisRepositoryFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;
import org.springframework.data.repository.config.RepositoryConfigurationSource;

/**
 * @author jarvis@caomeitu.com
 * @date 15/9/8
 */
public class MybatisRepositoryConfigExtension extends RepositoryConfigurationExtensionSupport {

    @Override
    protected String getModulePrefix() {
        return "mybatis";
    }

    @Override
    public String getRepositoryFactoryClassName() {
        return MyBatisRepositoryFactoryBean.class.getName();
    }

    @Override
    public void postProcess(BeanDefinitionBuilder builder, RepositoryConfigurationSource source) {


    }

//    @Override
//    public void registerBeansForRoot(BeanDefinitionRegistry registry, RepositoryConfigurationSource config) {
//        super.registerBeansForRoot(registry, config);
//
//        Object source = config.getSource();
//
//        registerIfNotAlreadyRegistered(new RootBeanDefinition(MybatisMappingContextFactoryBean.class), registry,
//                MYBATIS_MAPPING_CONTEXT_BEAN_NAME, source);
//
//    }
}