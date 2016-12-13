package com.sccl.YbZ.springboot.utils.codeBulider.daoBulider;

import com.sccl.YbZ.springboot.common.Constant;
import com.sccl.YbZ.springboot.utils.SpellUtil;
import com.sccl.YbZ.springboot.utils.codeBulider.AutoCodeUtil;
import com.sccl.YbZ.springboot.utils.codeBulider.CommonCodeBulider;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.StringWriter;

/**
 * Created by zyb on 2016/12/13.
 */
public class DaoImplBulider  implements CommonCodeBulider{

    /**
     * daoVM路径
     */
    private String daoImplVMPath = "templates/codeBulider/dao/daoImplTemplate.vm";

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
     * @param tableName 数据表名称
     * @return
     * @throws Exception
     */
    @Override
    public String createCode(String tableName) throws Exception {
        Template template = AutoCodeUtil.getTemplate(daoImplVMPath);
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
        String packagePath = "src/main/java/com/sccl/YbZ/springboot/dao/impl/";
        String filaName = packagePath + daoName + "Impl" + Constant.suffix;
        return filaName;
    }

    public static void main(String[] args) {
        DaoImplBulider dib = new DaoImplBulider();
        try {
            AutoCodeUtil.createFile("user",dib);
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
