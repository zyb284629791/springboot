package com.sccl.YbZ.springboot.common;


import com.sccl.YbZ.springboot.utils.NullUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * service层基类
 * 由于接口内属性均为静态常量，故无法使用泛型属性，所以此处直接用实现类来代替而抛弃接口
 * Created by zyb on 2016/12/12.
 */
public class EntityManager<T,PK extends Serializable> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 注入dao，spring会根据子类泛型自动注入对应类型的dao
     */
    @Autowired
    public BaseDao<T,PK> dao;

    /**
     * 新增
     * @param entity
     */
    public void save(T entity) {
        dao.save(entity);
    }

    /**
     * 根据主键查询单个实体
     * @param id
     * @return
     */
    public T findById(PK id) {
        T entity = dao.getById(id);
        return entity;
    }

    /**
     * 全查整张表
     * @return
     */
    public List<T> findAll(){
        List<T> entityList = dao.getAll();
        return entityList;
    }

    /**
     * 更新实体
     * @param entity
     */
    public void update(T entity) {
        dao.update(entity);
    }

    /**
     * 根据主键删除
     * @param id
     */
    public void deleteById(PK id) {
        dao.deleteById(id);
    }

    /**
     * 根据主键批量删除
     * @param ids
     */
    public void batchDelete(List<PK> ids) {
        dao.batchDelete(ids);
    }

    /**
     * 根据条件查询一条记录
     * @param propertities 查询条件 ---> “字段名”：“值”
     * @return
     */
    public T findOneByPropertity(Map<String,Object> propertities){
        return findOneWithOrderBy(propertities, null);
    }

    /**
     * 根据条件，排序查找一条记录
     * @param propertities
     * @param orderBy
     * @return
     */
    public T findOneWithOrderBy(Map<String,Object> propertities,Map<String,Boolean> orderBy){
        List<T> entityList = findByPropertityWithOrderBy(propertities,orderBy);
        if (NullUtil.isNotNullCollection(entityList)) {
            return entityList.get(0);
        }
        return null;
    }

    /**
     * 根据条件查询记录
     * @param propertities
     * @return
     */
    public List<T> findByPropertity(Map<String,Object> propertities){
        if (NullUtil.isNullMap(propertities)) {
            logger.error("参数为空...");
            return null;
        }
        return findByPropertityWithOrderBy(propertities,null);
    }

    /**
     * 根据条件、排序查询记录
     * @param propertities
     * @param orderBy
     * @return
     */
    public List<T> findByPropertityWithOrderBy(Map<String, Object> propertities, Map<String, Boolean> orderBy) {
        return dao.findByPropertity(propertities,orderBy);
    }
}
