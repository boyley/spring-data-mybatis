package com.github.mybatis.repository.support;

import com.github.mybatis.repository.query.MybatisQueryLookupStrategy;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Persistable;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;

import java.io.Serializable;

/**
 * Mybatis Repository Factory.
 *
 * @author songjiawei
 * @date 15/9/8
 */
public class MybatisRepositoryFactory extends RepositoryFactorySupport {

    private final SqlSessionTemplate sqlSessionTemplate;
    private EntityInformation entityInformation = null;
    private AuditorAware auditorAware;

    public MybatisRepositoryFactory(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;

    }

    @Override
    public <T, ID extends Serializable> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        if (null != entityInformation) return entityInformation;

        if (Persistable.class.isAssignableFrom(domainClass)) {
            entityInformation = new MybatisPersistableEntityInformation(domainClass);
        } else {
            entityInformation = new MybatisEntityInformation<T, ID>(domainClass);
        }
        return entityInformation;
    }

    /**
     * @param repositoryInformation
     * @return
     */
    @Override
    protected Object getTargetRepository(RepositoryInformation repositoryInformation) {

        // generate auto mapping xml file for this repository
        generateAutoMappingXml(repositoryInformation);

        SimpleMybatisRepository repository = getTargetRepositoryViaReflection(repositoryInformation,
                repositoryInformation,
                getEntityInformation(repositoryInformation.getDomainType()),
                sqlSessionTemplate);

        repository.setAuditorAware(auditorAware);

        return repository;
    }


    /**
     * generate auto mapping xml file for this repository
     *
     * @param repositoryInformation
     */
    private void generateAutoMappingXml(RepositoryInformation repositoryInformation) {
//        new BasicMappingGenerator(sqlSessionTemplate.getConfiguration(), repositoryInformation).generate();
//        new InitiallyMappingGenerator(sqlSessionTemplate.getConfiguration(), repositoryInformation.getDomainType()).generate();
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata repositoryMetadata) {
        return SimpleMybatisRepository.class;
    }

    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(QueryLookupStrategy.Key key, EvaluationContextProvider evaluationContextProvider) {
        return new MybatisQueryLookupStrategy(sqlSessionTemplate, evaluationContextProvider);
    }

    public void setAuditorAware(AuditorAware auditorAware) {
        this.auditorAware = auditorAware;
    }
}