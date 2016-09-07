package com.github.mybatis.repository.query;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.RepositoryQuery;

import java.lang.reflect.Method;

/**
 * @author jarvis@caomeitu.com
 * @date 15/9/8
 */
public class MybatisQueryLookupStrategy implements QueryLookupStrategy {
    private final SqlSessionTemplate sqlSessionTemplate;
    private final EvaluationContextProvider evaluationContextProvider;

    public MybatisQueryLookupStrategy(SqlSessionTemplate sqlSessionTemplate, EvaluationContextProvider evaluationContextProvider) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.evaluationContextProvider = evaluationContextProvider;
    }

    @Override
    public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory, NamedQueries namedQueries) {
        return new MybatisRepositoryQuery(sqlSessionTemplate, new MybatisQueryMethod(method, metadata));
    }
}