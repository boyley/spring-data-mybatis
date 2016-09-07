package com.github.mybatis.repository.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Mybatis Repository Basic Interface.
 *
 * @author jarvis@caomeitu.com
 * @date 15/9/8
 */
@NoRepositoryBean
public interface MybatisRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    <X extends T> Page<T> findAll(Pageable pageable, X condition);

    <X extends T> Iterable<T> findAll(X condition);

    <X extends T> Iterable<T> findAll(Sort sort, X condition);

    <X extends T> T findOne(X condition);

    <X extends T> Page<T> findBasicAll(Pageable pageable, X condition);

    <X extends T> Iterable<T> findBasicAll(X condition);

    <X extends T> Iterable<T> findBasicAll(Sort sort, X condition);

    <X extends T> Long countBasicAll(X condition);

    <X extends T> Long countAll(X condition);

    <X extends T> T findBasicOne(X condition);


    T findBasicOne(ID id);

    <T> Class<T> getDomainType();
}