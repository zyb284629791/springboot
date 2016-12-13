package com.sccl.YbZ.springboot.common;
import com.sccl.YbZ.springboot.utils.NullUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * dao层基类
 * Created by zyb on 2016/12/08.
 */
public class BaseDaoImpl<T,PK extends Serializable> implements BaseDao<T,PK> {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 当前类class
     */
    private Class<T> persistentClass;

    /**
     * 类所对应的表名
     */
    private String tableName;

    /**
     * 主键列所对应的get方法名
     */
    private String primaryKey;

    public BaseDaoImpl() {
        Type genericType = this.getClass().getGenericSuperclass();
        //获取类对应class
        persistentClass = (Class) ((ParameterizedType) genericType).getActualTypeArguments()[0];

        //获取表名
        tableName = persistentClass.getAnnotation(Table.class).name();

        //获取属性名
        Method[] methods = persistentClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Id.class)) {
                primaryKey = method.getAnnotation(Column.class).name();
            }
        }
    }

    @Override
    public void save(T entity) {
        StringBuffer sql = new StringBuffer("insert into " + tableName + "(");
        Map<String, Object> paramMap = initSqlParam(entity);
        sql.append(StringUtils.join(paramMap.keySet(),","));
        sql.append(") values(:").append(StringUtils.join(paramMap.keySet(), ",:"));
        sql.append(")");
        logger.debug(sql.toString());
        namedParameterJdbcTemplate.update(sql.toString().intern(), paramMap);
    }

    @Override
    public void update(T entity) {
        StringBuffer sql = new StringBuffer("update ").append(tableName).append(" t set ");
        Map<String, Object> paramMap = this.initSqlParam(entity);
        for (String col : paramMap.keySet()) {
            if (!primaryKey.equalsIgnoreCase(col)) {
                sql.append(" t.").append(col).append(" = :").append(col).append(",");
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" where t.").append(primaryKey).append(" = :").append(primaryKey);
        logger.debug("update sql --- > " + sql.toString());
        namedParameterJdbcTemplate.update(sql.toString().intern(), paramMap);
    }

    @Override
    public T getById(PK id) {
        StringBuffer sql = new StringBuffer("select * from ").append(tableName);
        sql.append(" t where t.").append(primaryKey).append(" = :id");
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id", id);
        logger.debug("getById sql --- > " + sql.toString());
        T entity = namedParameterJdbcTemplate.queryForObject(sql.toString().intern(),paramMap,
                BeanPropertyRowMapper.newInstance(persistentClass));
        return entity;
    }

    @Override
    public List<T> getAll() {
        StringBuffer sql = new StringBuffer("select * from ").append(tableName);
        Map<String, Object> paramMap = new HashMap<String,Object>();
        logger.debug("getAll sql --- > " + sql.toString());
        return namedParameterJdbcTemplate.queryForList(sql.toString().intern(),paramMap,persistentClass);
    }

    @Override
    public void deleteById(PK id) {
        String sql = "delete from " + tableName + " t where t." + primaryKey + " = :id";
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id", id);
        logger.debug("delete sql --- > " + sql);
        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public void batchDelete(List<PK> ids) {
        String sql = "delete from " + tableName + " t where t." + primaryKey + " in (:ids)";
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("ids", ids);
        logger.debug("batch delete sql --- > " + sql);
        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    /**
     * 根据反射拼接sql字段
     * @param entity
     * @return
     */
    private Map<String, Object> initSqlParam(T entity) {
        Map<String, Object> paramMap = new HashMap<>();
        Method[] methods = entity.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Column.class)) {
                String columnName = method.getAnnotation(Column.class).name();
                try {
                    Object val = method.invoke(entity);
                    paramMap.put(columnName, val);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return paramMap;
    }

    @Override
    public List<T> findByPropertity(Map<String, Object> propertities, Map<String, Boolean> orderBy) {
        StringBuffer sql = new StringBuffer("select * from ").append(tableName).append(" t where ");
        for (String col : propertities.keySet()) {
            sql.append("t.").append(col).append(" = :").append(col);
        }
        if (NullUtil.isNotNullMap(orderBy)) {
            sql.append(" order by ");
            for (String key : orderBy.keySet()) {
                sql.append(key).append(orderBy.get(key) ? " desc" : "asc");
            }
        }
        logger.debug("find by propertity sql --- > " + sql.toString());
        return namedParameterJdbcTemplate.query(sql.toString().intern(),propertities,
                BeanPropertyRowMapper.newInstance(persistentClass));
    }
}
