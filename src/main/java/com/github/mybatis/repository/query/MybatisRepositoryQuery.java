package com.github.mybatis.repository.query;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.mapping.model.MappingException;
import org.springframework.data.repository.query.Parameter;
import org.springframework.data.repository.query.Parameters;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author jarvis@caomeitu.com
 * @date 15/9/8
 */
public class MybatisRepositoryQuery implements RepositoryQuery {

    protected final SqlSessionTemplate sqlSessionTemplate;
    protected final MybatisQueryMethod method;

    public MybatisRepositoryQuery(SqlSessionTemplate sqlSessionTemplate, MybatisQueryMethod method) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.method = method;
    }


    @Override
    public Object execute(Object[] parameters) {
        String mappedStatementId = method.getMappedStatementId();
        boolean hasStatement = sqlSessionTemplate.getConfiguration().hasStatement(mappedStatementId);
        if (hasStatement) {
            return executeFromMapper(mappedStatementId, parameters);
        }
        throw new MappingException("can not find mapped statement for method : " + method.getName());
        // TODO  PartTree Auto create mapped statement.
    }

    private Object executeFromMapper(String mappedStatementId, Object[] parameters) {
        Map<String, Object> params = new HashMap<String, Object>();
        Parameters<?, ?> methodParameters = method.getParameters();
        if (null != methodParameters) {
            Iterator<? extends Parameter> iterator = methodParameters.iterator();
            while (iterator.hasNext()) {
                Parameter parameter = iterator.next();
                params.put(parameter.getName(), parameters[parameter.getIndex()]);
            }
        }
        if (method.isStreamQuery()) {
        } else if (method.isSliceQuery()) {
        } else if (method.isCollectionQuery()) {
            return sqlSessionTemplate.selectList(mappedStatementId, params);
        } else if (method.isPageQuery()) {
        } else if (method.isQueryForEntity()) {
            return sqlSessionTemplate.selectOne(mappedStatementId, params);
        }

        return sqlSessionTemplate.selectOne(mappedStatementId, params);

    }


    @Override
    public QueryMethod getQueryMethod() {
        return method;
    }

}