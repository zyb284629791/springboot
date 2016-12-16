package com.sccl.YbZ.springboot.utils.codeBulider.controllerBulider;

import com.sccl.YbZ.springboot.common.Constant;
import com.sccl.YbZ.springboot.utils.SpellUtil;
import com.sccl.YbZ.springboot.utils.codeBulider.CommonBulider;
import com.sccl.YbZ.springboot.utils.codeBulider.CreateCodeCallBack;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.io.StringWriter;

/**
 * restController生成器
 * 读取restControllerVM 生成代码
 * Created by zyb on 2016/11/16.
 */
public class RestControllerBulider implements CreateCodeCallBack {

    /**
     * restControllerVM路径
     */
    private String restControllerVMPath = "templates/codeBulider/controller/restControllerTemplate.vm";

    /**
     * 实体名
     */
    private String entityName = null;

    /**
     * 文件名
     */
    private String controllerName = null;


    /**
     * 根据模板生成代码
     *
     * @param tableName 数据表名称
     * @return
     * @throws Exception
     */
    @Override
    public String createCode(String tableName) throws Exception {
        Template template = CommonBulider.getTemplate(restControllerVMPath);
        VelocityContext velocityContext = CommonBulider.getVelocityContext();
        velocityContext.put("tableName", tableName);
        entityName = SpellUtil.toPascalCase(tableName);
        controllerName = entityName + "Controller";
        velocityContext.put("entity", entityName);
        velocityContext.put("controllerName", controllerName);
        String serviceName = entityName + "Service";
        String serviceAnnotation = SpellUtil.toCamelCase(tableName) + "Service";
        String annotationName = SpellUtil.toCamelCase(tableName) + "Controller";
        velocityContext.put("serviceAnnotation", serviceAnnotation);
        velocityContext.put("serviceName", serviceName);
        velocityContext.put("annotationName", annotationName);
        String PK = CommonBulider.getPKType(tableName);
        velocityContext.put("PK", PK);
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public String getFileName(String tableName,String packagePath) {
        String filaName = packagePath + controllerName + Constant.suffix;
        return filaName;
    }

}
