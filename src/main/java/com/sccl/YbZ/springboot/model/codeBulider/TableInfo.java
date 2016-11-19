package com.sccl.YbZ.springboot.model.codeBulider;

import java.util.List;
import java.util.Map;

/**
 * 2、model层生成时需要准备数据表信息，表信息至少包含表名、主键信息、常规字段。字段中至少需要包含字段名，字段类型等信息。
 *  创建TableInfo和Column Info类
 *  spring boot 配置JDBC,连接数据库
 * Created by zyb on 2016/11/16.
 */
public class TableInfo {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 对应实体名
     */
    private String className;

    /**
     * 主键信息
     */
    private Map<String,ColumnInfo> primaryKeys;

    /**
     * 字段信息
     */
    private Map<String,ColumnInfo> columnInfos;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, ColumnInfo> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(Map<String, ColumnInfo> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public Map<String, ColumnInfo> getColumnInfos() {
        return columnInfos;
    }

    public void setColumnInfos(Map<String, ColumnInfo> columnInfos) {
        this.columnInfos = columnInfos;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
