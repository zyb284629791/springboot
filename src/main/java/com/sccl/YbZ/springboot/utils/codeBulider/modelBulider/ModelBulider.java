package com.sccl.YbZ.springboot.utils.codeBulider.modelBulider;

import com.sccl.YbZ.springboot.common.CodeBuliderException;
import com.sccl.YbZ.springboot.common.Constant;
import com.sccl.YbZ.springboot.model.codeBulider.ColumnInfo;
import com.sccl.YbZ.springboot.model.codeBulider.TableInfo;
import com.sccl.YbZ.springboot.utils.SpellUtil;
import com.sccl.YbZ.springboot.utils.codeBulider.CommonBulider;
import com.sccl.YbZ.springboot.utils.codeBulider.CreateCodeCallBack;
import com.sccl.YbZ.springboot.utils.codeBulider.DBUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * model生成器
 * 读取数据表，封装table Info和column Info
 * 读取modelVM 生成代码
 * Created by zyb on 2016/11/16.
 */
public class ModelBulider implements CreateCodeCallBack {

    /**
     * modelVM路径
     */
    private java.lang.String modelVMPath = "templates/codeBulider/model/modelTemplateWithJPA.vm";
    /**
     *
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     *
     */
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DBUtil.getDataSource());


    /**
     * 根据模板生成代码
     *
     * @param tableName 数据表名称
     * @return
     * @throws Exception
     */
    @Override
    public String createCode(String tableName) throws Exception {
        Template template = CommonBulider.getTemplate(modelVMPath);
        VelocityContext velocityContext = CommonBulider.getVelocityContext();
        TableInfo tableInfo = getTableInfoByTableName(tableName);
        velocityContext.put("tableInfo", tableInfo);
        velocityContext.put("className", tableInfo.getClassName());
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public String getFileName(String tableName,String packagePath) {
        String fileName = packagePath.endsWith(File.separator) ? packagePath + SpellUtil.toPascalCase(tableName) +
                Constant.suffix : packagePath + File.separator + SpellUtil.toPascalCase(tableName) + Constant.suffix;
        return fileName;
    }

    /**
     * 封装数据表信息
     * @param tableName
     * @return
     */
    private TableInfo getTableInfoByTableName(String tableName) {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableName(tableName);
        tableInfo.setClassName(SpellUtil.toPascalCase(tableName));
        try {
            DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
            ResultSet colrs = metaData.getColumns(null, null, tableName, "%");
            ResultSet pkrs = metaData.getPrimaryKeys(null, null, tableName);
            Map<String,ColumnInfo> columnInfos = new HashMap<String,ColumnInfo>();
            Map<String,ColumnInfo> primaryKeys = new HashMap<String,ColumnInfo>();
            while (pkrs.next()) {
                String columnName = pkrs.getString("COLUMN_NAME");//列名
                //由于getprimarykeys得到的主键不包含列类型，描述等信息，故此处仅获取列名，通过getcolumns得到的信息重新封装
                ColumnInfo columnInfo = new ColumnInfo();
                primaryKeys.put(columnName,columnInfo);
            }
            if (primaryKeys.size()> 1) {
                logger.error("暂不支持联合主键");
                throw new CodeBuliderException("暂不支持联合主键");
            } else if (primaryKeys.size() == 0) {
                logger.error("当前表" + tableName + "无主键,无法生成");
                throw new CodeBuliderException("当前表" + tableName + "无主键,无法生成");
            }
            while (colrs.next()) {
                String columnName = colrs.getString("COLUMN_NAME");//列名
                ColumnInfo columnInfo = getColumnInfo(colrs, columnName);
                if (primaryKeys.get(columnName) == null) {
                    columnInfos.put(columnName,columnInfo);
                }else{
                    primaryKeys.put(columnName,columnInfo);
                }
            }
            tableInfo.setPrimaryKeys(primaryKeys);
            tableInfo.setColumnInfos(columnInfos);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableInfo;
    }

    /**
     * 解析result set并封装
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    private ColumnInfo getColumnInfo(ResultSet rs, String columnName) throws SQLException {
        ColumnInfo columnInfo = new ColumnInfo();
        int dataType = rs.getInt("DATA_TYPE"); //对应的java.sql.Types类型
//                String dataTypeName = rs.getString("TYPE_NAME");//java.sql.Types类型   名称
        String remarks = rs.getString("REMARKS");//列描述
        columnInfo.setColumnName(columnName);
        columnInfo.setColmunDescription(remarks);
        columnInfo.setColumnType(dataType);
        columnInfo.setFieldType(CommonBulider.transferColumnType(dataType));
        columnInfo.setFieldName(SpellUtil.toCamelCase(columnName));
        columnInfo.setPascalCaseFieldName(SpellUtil.toPascalCase(columnName));
        return columnInfo;
    }

}
