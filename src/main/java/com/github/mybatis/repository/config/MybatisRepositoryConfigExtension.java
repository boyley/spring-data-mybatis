package com.github.mybatis.repository.config;

import com.github.mybatis.repository.support.MyBatisRepositoryFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.data.repository.config.AnnotationRepositoryConfigurationSource;
import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.data.repository.config.XmlRepositoryConfigurationSource;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

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
    public void postProcess(BeanDefinitionBuilder builder, XmlRepositoryConfigurationSource config) {

        Element element = config.getElement();

        postProcess(builder, element.getAttribute("transaction-manager-ref"),
                element.getAttribute("sql-session-template-ref"), config.getSource());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport#postProcess(org.springframework.beans.factory.support.BeanDefinitionBuilder, org.springframework.data.repository.config.AnnotationRepositoryConfigurationSource)
     */
    @Override
    public void postProcess(BeanDefinitionBuilder builder, AnnotationRepositoryConfigurationSource config) {

        AnnotationAttributes attributes = config.getAttributes();

        postProcess(builder, attributes.getString("transactionManagerRef"),
                attributes.getString("sqlSessionTemplateRef"), config.getSource());
    }

    private void postProcess(BeanDefinitionBuilder builder, String transactionManagerRef, String sqlSessionTemplateRef,
                             Object source) {

        //TODO : transactionManagerRef

        if (StringUtils.hasText(sqlSessionTemplateRef)) {
            builder.addPropertyReference("sqlSessionTemplate", sqlSessionTemplateRef);
        }
        else {
            builder.addPropertyReference("sqlSessionTemplate","sqlSessionTemplate");
        }
    }

}