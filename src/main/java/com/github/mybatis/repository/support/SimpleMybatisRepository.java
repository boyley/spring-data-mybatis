package com.github.mybatis.repository.support;

import org.joda.time.DateTime;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.domain.*;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple Mybatis Repository.
 *
 * @author jarvis@caomeitu.com
 * @date 15/9/8
 */
public class SimpleMybatisRepository<T, ID extends Serializable> extends SqlSessionRepositorySupport implements MybatisRepository<T, ID> {

    private final RepositoryInformation repositoryInformation;
    private final EntityInformation<T, ID> entityInformation;

    private AuditorAware auditorAware;

    public SimpleMybatisRepository(RepositoryInformation repositoryInformation, EntityInformation<T, ID> entityInformation, SqlSessionTemplate sqlSessionTemplate) {
        super(sqlSessionTemplate);
        Assert.notNull(repositoryInformation);
        Assert.notNull(entityInformation);
        this.repositoryInformation = repositoryInformation;
        this.entityInformation = entityInformation;
    }

    public void setAuditorAware(AuditorAware auditorAware) {
        this.auditorAware = auditorAware;
    }

    @Override
    public <T> Class<T> getDomainType() {
        return (Class<T>) repositoryInformation.getDomainType();
    }

    @Override
    protected String getNamespace() {
//        return this.repositoryInformation.getRepositoryInterface().getName();
        return getDomainType().getName();
    }


    @Override
    public Page<T> findAll(Pageable pageable) {
        return findAll(pageable, null);
    }

    @Override
    public <S extends T> S save(S entity) {
        boolean isNew = entityInformation.isNew(entity);
        if (isNew) {
            if (entity instanceof Auditable) {
                ((Auditable) entity).setCreatedDate(DateTime.now());
                if (null != auditorAware) {
//                    ((Auditable) entity).setCreatedBy(auditorAware.getCurrentAuditor().g);
                }
            }
            insert("_insert", entity);
        } else {
            if (entity instanceof Auditable) {
                ((Auditable) entity).setLastModifiedDate(DateTime.now());
                if (null != auditorAware) {
//                    ((Auditable) entity).setLastModifiedBy(auditorAware.getCurrentAuditor());
                }
            }
            update("_update", entity);
        }
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        if (null == entities) return entities;
        for (S entity : entities) {
            save(entity);
        }
        return entities;
    }

    @Override
    public T findBasicOne(ID id) {
        return selectOne("_getBasicById", id);
    }

    @Override
    public T findOne(ID id) {
        return selectOne("_getById", id);
    }

    @Override
    public boolean exists(ID id) {
        return null != findOne(id);
    }

    @Override
    public Iterable<T> findAll() {
        return findAll((T) null);
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> ids) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("ids", ids);
        return selectList("_selectByIds", params);
    }

    @Override
    public long count() {
        return selectOne("_countByPager");
    }

    @Override
    public void delete(ID id) {
        super.delete("_deleteById", id);
    }

    @Override
    public void delete(T entity) {
        ID id = entityInformation.getId(entity);
        delete(id);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        if (null == entities) return;
        for (T entity : entities) {
            delete(entity);
        }
    }

    @Override
    public void deleteAll() {
        super.delete("_deleteAll");
    }

    @Override
    public <X extends T> T findOne(X condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        params.put("_complexeQuery", true);
        return selectOne("_selectByPager", params);
    }

    @Override
    public Iterable<T> findAll(Sort sort) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sorts", sort);
        params.put("_complexeQuery", true);
        return selectList("_selectByPager", params);
    }

    @Override
    public <X extends T> Page<T> findAll(Pageable pageable, X condition) {
        Map<String, Object> otherParam = new HashMap<String, Object>();
        otherParam.put("_complexeQuery", true);
        return findByPager(pageable, "_selectByPager", "_countByPager", condition, otherParam);
    }

    @Override
    public <X extends T> Iterable<T> findAll(X condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        params.put("_complexeQuery", true);
        return selectList("_selectByPager", params);
    }

    @Override
    public <X extends T> Iterable<T> findAll(Sort sort, X condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        params.put("sorts", sort);
        params.put("_complexeQuery", true);
        return selectList("_selectByPager", params);
    }

    @Override
    public <X extends T> Page<T> findBasicAll(Pageable pageable, X condition) {
        return findByPager(pageable, "_selectBasicByPager", "_countBasicByPager", condition);
    }

    @Override
    public <X extends T> Iterable<T> findBasicAll(X condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        return selectList("_selectBasicByPager", params);
    }

    @Override
    public <X extends T> Iterable<T> findBasicAll(Sort sort, X condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        params.put("sorts", sort);
        return selectList("_selectBasicByPager", params);
    }

    @Override
    public <X extends T> T findBasicOne(X condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        return selectOne("_selectBasicByPager", params);
    }

    @Override
    public <X extends T> Long countBasicAll(X condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        return selectOne("_countBasicByPager", params);
    }

    @Override
    public <X extends T> Long countAll(X condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        return selectOne("_countByPager", params);
    }
}