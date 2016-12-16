package com.sccl.YbZ.springboot.utils.codeBulider.serviceBulider;

import com.sccl.YbZ.springboot.common.Constant;
import com.sccl.YbZ.springboot.utils.SpellUtil;
import com.sccl.YbZ.springboot.utils.codeBulider.CommonBulider;
import com.sccl.YbZ.springboot.utils.codeBulider.CreateCodeCallBack;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.StringWriter;

/**
 * service生成器
 * 读取serviceVM 生成代码
 * Created by zyb on 2016/11/16.
 */
public class ServiceBulider implements CreateCodeCallBack {

    /**
     * serviceVM路径
     */
    private String serviceVMPath = "templates/codeBulider/service/serviceTemplate.vm";

    /**
     * 实体名
     */
    private String entityName = null;

    /**
     * 文件名
     */
    private String serviceName = null;


    /**
     * 根据模板生成代码
     *
     * @param tableName 数据表名称
     * @return
     * @throws Exception
     */
    @Override
    public String createCode(String tableName) throws Exception {
        Template template = CommonBulider.getTemplate(serviceVMPath);
        VelocityContext velocityContext = CommonBulider.getVelocityContext();
        velocityContext.put("tableName", tableName);
        entityName = SpellUtil.toPascalCase(tableName);
        serviceName = entityName + "Service";
        velocityContext.put("entity", entityName);
        velocityContext.put("serviceName", serviceName);
        String annotationName = SpellUtil.toCamelCase(tableName) + "Service";
        velocityContext.put("annotationName", annotationName);
        String PK = CommonBulider.getPKType(tableName);
        velocityContext.put("PK", PK);
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public String getFileName(String tableName,String packagePath) {
        String filaName = packagePath + serviceName + Constant.suffix;
        return filaName;
    }

}
