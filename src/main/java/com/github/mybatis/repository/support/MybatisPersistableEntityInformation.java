package com.github.mybatis.repository.support;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;

/**
 * @author jarvis@caomeitu.com
 * @date 15/9/8
 */
public class MybatisPersistableEntityInformation<T extends Persistable<ID>, ID extends Serializable> extends MybatisEntityInformation<T, ID> {
    public MybatisPersistableEntityInformation(Class<T> domainClass) {
        super(domainClass);
    }

    @Override
    public boolean isNew(T entity) {
        return entity.isNew();
    }

    @Override
    public ID getId(T t) {
        return t.getId();
    }
}
