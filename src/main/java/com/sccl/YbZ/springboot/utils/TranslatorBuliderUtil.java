package com.sccl.YbZ.springboot.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.*;

/**
 * Created by zyb on 2016/11/15.
 */
public class TranslatorBuliderUtil {

    private static final String TEMPLATE_VM_PATH = "templates/translatorTemplate.vm";
    private static final String PACKAGE_PATH = "src/main/java/com/sccl/YbZ/springboot/translator/";
    private static final String SUFFIX = "DataTranslator";

    public static void main(String[] args) {
        TranslatorBuliderUtil translatorBuliderUtil = new TranslatorBuliderUtil();
        try {
            String code = translatorBuliderUtil.createCode(TEMPLATE_VM_PATH);
            System.out.println(code);
//            translatorBuliderUtil.createFile();
//            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建bean的Service的实现<br>
     *
     * @throws Exception
     */
    public void createFile() throws Exception {
        String fileName = PACKAGE_PATH + getClassName() + SUFFIX + ".java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(createCode(TEMPLATE_VM_PATH));
        fw.flush();
        fw.close();
    }

    /**
     * 根据模板生成代码
     *
     * @param fileVMPath 模板路径
     * @return
     * @throws Exception
     */
    public String createCode(String fileVMPath) throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("input.encoding", "UTF-8");
        velocityEngine.setProperty("output.encoding", "UTF-8");
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(fileVMPath);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tableName",getTableName());
        velocityContext.put("className",getClassName());
        velocityContext.put("date",getDate());
        velocityContext.put("isChangeTableName",getIsChangeTableName());
        velocityContext.put("midTableName",getMidTableName());
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    /**
     * 创建文件
     *
     * @param file
     */
    public void createFilePath(File file) {
        if (!file.exists()) {
            System.out.println("创建[" + file.getAbsolutePath() + "]情况：" + file.mkdirs());
        } else {
            System.out.println("存在目录：" + file.getAbsolutePath());
        }
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public static String getDate() {
        return TimeUtil.currentTime(TimeUtil.DOC_FORMATOR_YMD);
    }

    public static String getTableName() {
        return "t_a_user";
    }

    public static boolean getIsChangeTableName() {
        return false;
    }

    public static String getMidTableName() {
        return "";
    }

    public static String getClassName() {
        String className = SpellUtil.toPascalCase(getTableName());
        return className;
    }
}