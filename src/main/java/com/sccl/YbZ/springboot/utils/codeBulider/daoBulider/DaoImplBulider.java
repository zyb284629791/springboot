package com.sccl.YbZ.springboot.utils.codeBulider.daoBulider;

import com.sccl.YbZ.springboot.common.Constant;
import com.sccl.YbZ.springboot.utils.SpellUtil;
import com.sccl.YbZ.springboot.utils.codeBulider.CommonBulider;
import com.sccl.YbZ.springboot.utils.codeBulider.CreateCodeCallBack;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.StringWriter;

/**
 * Created by zyb on 2016/12/13.
 */
public class DaoImplBulider  implements CreateCodeCallBack {

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
        Template template = CommonBulider.getTemplate(daoImplVMPath);
        VelocityContext velocityContext = CommonBulider.getVelocityContext();
        velocityContext.put("tableName", tableName);
        entityName = SpellUtil.toPascalCase(tableName);
        daoName = entityName + "Dao";
        velocityContext.put("entity", entityName);
        velocityContext.put("daoName", daoName);
        String PK = CommonBulider.getPKType(tableName);
        velocityContext.put("PK", PK);
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public String getFileName(String tableName,String packagePath) {
        String filaName = packagePath + daoName + "Impl" + Constant.suffix;
        return filaName;
    }

}
