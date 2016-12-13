package com.sccl.YbZ.springboot.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zyb on 2016/12/08.
 */
public interface BaseDao<T,PK extends Serializable>  {

    void update(T entity);

    void save(T entity);

    T getById(PK id);

    List<T> getAll();

    void deleteById(PK id);

    void batchDelete(List<PK> ids);

    /**
     * 根据条件查询，是否排序
     * @param propertities
     * @param orderBy true表示需要倒序，false表示顺序
     * @return
     */
    List<T> findByPropertity(Map<String, Object> propertities, Map<String, Boolean> orderBy);
}
