package com.github.mybatis.repository.query;

import com.github.mybatis.annotations.MybatisQuery;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author jarvis@caomeitu.com
 * @date 15/9/14
 */
public class MybatisQueryMethod extends QueryMethod {
    private final RepositoryMetadata metadata;
    private final Method             method;
    private final Class<?> mapperInterface;

    /**
     * Creates a new {@link QueryMethod} from the given parameters. Looks up the correct query to use for following
     * invocations of the method given.
     *
     * @param method   must not be {@literal null}
     * @param metadata must not be {@literal null}
     */
    public MybatisQueryMethod(Method method, RepositoryMetadata metadata) {
        super(method, metadata,new SpelAwareProxyProjectionFactory());
        Assert.notNull(method, "Method must not be null!");
        Assert.notNull(metadata, "RepositoryMetadata must not be null!");
        this.metadata = metadata;
        this.method = method;
        mapperInterface = metadata.getRepositoryInterface();
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

    public Class<?> getRepositoryInterface() {
        return mapperInterface;
    }

    public Method getMethod() {
        return method;
    }

    public String getQuerySQL() {
        return null;
    }
}
