package com.sccl.YbZ.springboot.utils.codeBulider.daoBulider;

import com.sccl.YbZ.springboot.common.Constant;
import com.sccl.YbZ.springboot.utils.SpellUtil;
import com.sccl.YbZ.springboot.utils.TimeUtil;
import com.sccl.YbZ.springboot.utils.codeBulider.AutoCodeUtil;
import com.sccl.YbZ.springboot.utils.codeBulider.CommonCodeBulider;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.StringWriter;

/**
 * dao生成器
 * 读取daoVM 生成代码
 * Created by zyb on 2016/11/16.
 */
public class DaoBulider implements CommonCodeBulider {

    /**
     * daoVM路径
     */
    private String daoVMPath = "templates/codeBulider/dao/daoTemplate.vm";

    /**
     * 实体名
     */
    private String entityName = null;

    /**
     * 文件名
     */
    private String daoName = null;


    /**
     * 根据模板生成代码
     *
     * @param tableName 数据表名称
     * @return
     * @throws Exception
     */
    @Override
    public String createCode(String tableName) throws Exception {
        Template template = AutoCodeUtil.getTemplate(daoVMPath);
        VelocityContext velocityContext = AutoCodeUtil.getVelocityContext();
        entityName = SpellUtil.toPascalCase(tableName);
        daoName = entityName + "Dao";
        velocityContext.put("entity", entityName);
        velocityContext.put("daoName", daoName);
        String PK = AutoCodeUtil.getPKType(tableName);
        velocityContext.put("PK", PK);
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public String getFileName(String tableName) {
        String packagePath = "src/main/java/com/sccl/YbZ/springboot/dao/";
        String filaName = packagePath + daoName + Constant.suffix;
        return filaName;
    }

    public static void main(String[] args) {
        try {
            DaoBulider db = new DaoBulider();
            AutoCodeUtil.createFile("user",db);
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
