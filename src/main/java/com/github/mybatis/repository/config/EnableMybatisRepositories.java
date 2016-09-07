package com.github.mybatis.repository.config;

import com.github.mybatis.repository.support.MyBatisRepositoryFactoryBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author jarvis@caomeitu.com
 * @date 15/9/8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MybatisRepositoriesRegistrar.class)
public @interface EnableMybatisRepositories {


    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    ComponentScan.Filter[] includeFilters() default {};

    ComponentScan.Filter[] excludeFilters() default {};

    Class<?> repositoryFactoryBeanClass() default MyBatisRepositoryFactoryBean.class;

    String namedQueriesLocation() default "";

    String repositoryImplementationPostfix() default "Impl";

}