package com.sccl.YbZ.springboot.utils.codeBulider;

import com.alibaba.druid.sql.visitor.functions.If;
import com.google.common.collect.Lists;
import com.sccl.YbZ.springboot.common.CodeBuliderException;
import com.sccl.YbZ.springboot.common.Constant;
import com.sccl.YbZ.springboot.utils.SpellUtil;
import com.sccl.YbZ.springboot.utils.TimeUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * Created by zyb on 2016/12/13.
 */
public class AutoCodeUtil {

    private static Logger logger = LoggerFactory.getLogger(AutoCodeUtil.class);

    private static JdbcTemplate jdbcTemplate = new JdbcTemplate(DBUtil.getDataSource());

    /**
     * 读取模板
     * @param vmPath
     * @return
     */
    public static Template getTemplate(String vmPath) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("input.encoding", "UTF-8");
        velocityEngine.setProperty("output.encoding", "UTF-8");
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        return velocityEngine.getTemplate(vmPath);
    }

    /**
     * 获取vm上下文
     * @return
     */
    public static VelocityContext getVelocityContext() {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("date", AutoCodeUtil.getDate());
        velocityContext.put("author",AutoCodeUtil.getAuthor());
        return velocityContext;
    }

    /**
     * 生成类文件
     * @param tableName
     * @param ccb
     * @throws Exception
     */
    public static void createFile(String tableName, CommonCodeBulider ccb) throws Exception {
        FileWriter fw = null;
        String code = ccb.createCode(tableName);
        String fileName = ccb.getFileName(tableName);
        File file = new File(fileName);
        try {
            fw = new FileWriter(file);
            fw.write(code);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    /**
     * 返回当前日期
     * @return
     */
    public static String getDate() {
        String date = TimeUtil.currentTime(TimeUtil.FORMATOR_YMD_POINT);
        return date;
    }

    /**
     * 返回计算机用户
     * @return
     */
    public static String getAuthor() {
        String author = System.getProperty("user.name");
        return author;
    }

    /**
     * 获取主键类型
     * @param tableName
     * @return
     */
    public static String getPKType(String tableName) {
        String pkType = null;
        try {
            DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
            ResultSet colrs = metaData.getColumns(null, null, tableName, "%");
            ResultSet pkrs = metaData.getPrimaryKeys(null, null, tableName);
            List<String> pkName = Lists.newArrayList();
            while (pkrs.next()) {
                pkName.add(pkrs.getString("COLUMN_NAME"));
            }
            if (pkName.size() > 1) {
                logger.error("暂不支持联合主键");
                throw new CodeBuliderException("暂不支持联合主键");
            } else if (pkName.size() == 0) {
                logger.error("当前表" + tableName + "无主键,无法生成");
                throw new CodeBuliderException("当前表" + tableName + "无主键,无法生成");
            }
            while (colrs.next()) {
                String colName = colrs.getString("COLUMN_NAME");
                if (pkName.get(0).equalsIgnoreCase(colName)) {
                    pkType = transferColumnType(colrs.getInt("DATA_TYPE"));
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pkType;
    }

    /**
     * 从DB类型转换成java类型
     * @param dataType
     * @return
     */
    public static String transferColumnType(int dataType) {
        String columnType = "java.lang.String";
        switch (dataType){
            case Types.DOUBLE:
                columnType = "java.lang.Double";
                break;
            case Types.INTEGER:
                columnType = "java.lang.Integer";
                break;
            case Types.DATE:
            case Types.TIMESTAMP:
                columnType = "java.util.Date";
                break;
            case Types.BOOLEAN:
                columnType = "java.lang.Boolean";
                break;
            case Types.BLOB:
                throw new RuntimeException("暂不支持blob类型...");
        }
        return columnType;
    }
}
