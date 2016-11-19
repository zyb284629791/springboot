package com.sccl.YbZ.springboot.model.codeBulider;

/**
 * 2、model层生成时需要准备数据表信息，表信息至少包含表名、主键信息、常规字段。字段中至少需要包含字段名，字段类型等信息。
 *  创建TableInfo和Column Info类
 *  spring boot 配置JDBC,连接数据库
 * Created by zyb on 2016/11/16.
 */
public class ColumnInfo {

    /**
     * 列名
     */
    private String columnName;

    /**
     * 字段类型
     */
    private int columnType;

    /**
     * 列描述
     */
    private String colmunDescription;

    /**
     * 属性名 （从字段名转成驼峰形式）
     */
    private String fieldName;

    /**
     * 属性类型 --- 将属性类型转成对应的java类型 eg. java.lang.String
     */
    private String fieldType;

    /**
     * pascal形式属性名（getter/setter时使用）
     */
    private String PascalCaseFieldName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public String getColmunDescription() {
        return colmunDescription;
    }

    public void setColmunDescription(String colmunDescription) {
        this.colmunDescription = colmunDescription;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getPascalCaseFieldName() {
        return PascalCaseFieldName;
    }

    public void setPascalCaseFieldName(String pascalCaseFieldName) {
        PascalCaseFieldName = pascalCaseFieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}
