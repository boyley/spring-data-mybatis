package com.github.mybatis.repository.support;

import org.springframework.data.repository.core.support.AbstractEntityInformation;

import java.io.Serializable;

/**
 * // TODO
 * @author jarvis@caomeitu.com
 * @date 15/9/8
 */
public class MybatisEntityInformation<T, ID extends Serializable> extends AbstractEntityInformation<T, ID> {

    public MybatisEntityInformation(Class<T> domainClass) {
        super(domainClass);
    }

    @Override
    public ID getId(T t) {
        return null;
    }

    @Override
    public Class<ID> getIdType() {
        return null;
    }


}