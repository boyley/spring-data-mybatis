package com.github.mybatis.repository.query;

import com.github.mybatis.annotations.MybatisQuery;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author jarvis@caomeitu.com
 * @date 15/9/14
 */
public class MybatisQueryMethod extends QueryMethod {
    private final RepositoryMetadata metadata;
    private final Method             method;

    /**
     * Creates a new {@link QueryMethod} from the given parameters. Looks up the correct query to use for following
     * invocations of the method given.
     *
     * @param method   must not be {@literal null}
     * @param metadata must not be {@literal null}
     */
    public MybatisQueryMethod(Method method, RepositoryMetadata metadata) {
        super(method, metadata,null);
        this.metadata = metadata;
        this.method = method;
    }

    public String getNamespace() {
        return metadata.getRepositoryInterface().getName();
    }

    public String getMappedStatementId() {

        String queryName = String.format("%s.%s", metadata.getRepositoryInterface().getName(), method.getName());

        MybatisQuery mybatisQuery = method.getAnnotation(MybatisQuery.class);
        if (null != mybatisQuery && !StringUtils.isEmpty(mybatisQuery.value())) {
            queryName = mybatisQuery.value();
        }
        return queryName;
    }


    public String getQuerySQL() {
        return null;
    }
}
