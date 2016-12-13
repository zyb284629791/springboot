package com.sccl.YbZ.springboot.utils.codeBulider.controllerBulider;

import com.sccl.YbZ.springboot.common.Constant;
import com.sccl.YbZ.springboot.utils.SpellUtil;
import com.sccl.YbZ.springboot.utils.codeBulider.AutoCodeUtil;
import com.sccl.YbZ.springboot.utils.codeBulider.CommonCodeBulider;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.StringWriter;

/**
 * restController生成器
 * 读取restControllerVM 生成代码
 * Created by zyb on 2016/11/16.
 */
public class RestControllerBulider implements CommonCodeBulider {

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
        Template template = AutoCodeUtil.getTemplate(restControllerVMPath);
        VelocityContext velocityContext = AutoCodeUtil.getVelocityContext();
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
        String PK = AutoCodeUtil.getPKType(tableName);
        velocityContext.put("PK", PK);
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public String getFileName(String tableName) {
        String packagePath = "src/main/java/com/sccl/YbZ/springboot/controller/";
        String filaName = packagePath + controllerName + Constant.suffix;
        return filaName;
    }

    public static void main(String[] args) {
        try {
            RestControllerBulider rcb = new RestControllerBulider();
            AutoCodeUtil.createFile("user",rcb);
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
