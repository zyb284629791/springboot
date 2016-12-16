package com.sccl.YbZ.springboot.utils;

import com.sccl.YbZ.springboot.utils.codeBulider.CommonBulider;
import com.sccl.YbZ.springboot.utils.codeBulider.controllerBulider.RestControllerBulider;
import com.sccl.YbZ.springboot.utils.codeBulider.daoBulider.DaoBulider;
import com.sccl.YbZ.springboot.utils.codeBulider.daoBulider.DaoImplBulider;
import com.sccl.YbZ.springboot.utils.codeBulider.buliderFactory.impl.*;
import com.sccl.YbZ.springboot.utils.codeBulider.modelBulider.ModelBulider;
import com.sccl.YbZ.springboot.utils.codeBulider.serviceBulider.ServiceBulider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自动代码生共工具
 * Created by zyb on 2016/12/16.
 */
public class CodeBuliderUtil {

    private static Logger logger = LoggerFactory.getLogger(CodeBuliderUtil.class);

    /**
     * 创建model
     * @param tableName 表名
     * @param packagePath 包路径
     */
    public static void createModel(String tableName,String packagePath){
        if (NullUtil.isBlankString(tableName) || NullUtil.isBlankString(packagePath)) {
            logger.error("tableName,pacgagePath不能为空...");
            return;
        }
        ModelBuliderFactory factory = new ModelBuliderFactory();
        ModelBulider bulider = factory.getInstance();
        try {
            CommonBulider.createFile(tableName,packagePath,bulider);
        } catch (Exception e) {
            logger.error("model生成失败... " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    /**
     * 创建dao
     * @param tableName 表名
     * @param packagePath 包路径
     */
    public static void createDao(String tableName,String packagePath){
        if (NullUtil.isBlankString(tableName) || NullUtil.isBlankString(packagePath)) {
            logger.error("tableName,pacgagePath不能为空...");
            return;
        }
        DaoBuliderFactory factory = new DaoBuliderFactory();
        DaoBulider bulider = factory.getInstance();
        try {
            CommonBulider.createFile(tableName,packagePath,bulider);
        } catch (Exception e) {
            logger.error("dao生成失败... " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    /**
     * 创建daoImpl
     * @param tableName 表名
     * @param packagePath 包路径
     */
    public static void createDaoImpl(String tableName,String packagePath){
        if (NullUtil.isBlankString(tableName) || NullUtil.isBlankString(packagePath)) {
            logger.error("tableName,pacgagePath不能为空...");
            return;
        }
        DaoImplBuliderFactory factory = new DaoImplBuliderFactory();
        DaoImplBulider bulider = factory.getInstance();
        try {
            CommonBulider.createFile(tableName,packagePath,bulider);
        } catch (Exception e) {
            logger.error("daoImpl生成失败... " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    /**
     * 创建dao、daoImpl
     * @param tableName 表名
     * @param daoPackagePath 包路径
     */
    public static void createDaoAndImpl(String tableName,String daoPackagePath,String daoImplPackagePath){
        createDao(tableName,daoPackagePath);
        createDaoImpl(tableName,daoImplPackagePath);
    }

    /**
     * 创建service
     * @param tableName 表名
     * @param packagePath 包路径
     */
    public static void createService(String tableName,String packagePath){
        if (NullUtil.isBlankString(tableName) || NullUtil.isBlankString(packagePath)) {
            logger.error("tableName,pacgagePath不能为空...");
            return;
        }
        ServiceBuliderFactory factory = new ServiceBuliderFactory();
        ServiceBulider bulider = factory.getInstance();
        try {
            CommonBulider.createFile(tableName,packagePath,bulider);
        } catch (Exception e) {
            logger.error("service生成失败... " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    /**
     * 创建restController
     * @param tableName 表名
     * @param packagePath 包路径
     */
    public static void createRestController(String tableName,String packagePath){
        if (NullUtil.isBlankString(tableName) || NullUtil.isBlankString(packagePath)) {
            logger.error("tableName,pacgagePath不能为空...");
            return;
        }
        RestControllerBuliderFactory factory = new RestControllerBuliderFactory();
        RestControllerBulider bulider = factory.getInstance();
        try {
            CommonBulider.createFile(tableName,packagePath,bulider);
        } catch (Exception e) {
            logger.error("restController生成失败... " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    /**
     * model->dao->daoImpl->service->controller全部生成
     * 需要注意此方法生成的所有代码均在一起，需要自行分包
     * @param tableName
     * @param packagePath
     */
    public static void createCode(String tableName,String packagePath){
        createModel(tableName,packagePath);
        createDaoAndImpl(tableName,packagePath,packagePath);
        createService(tableName,packagePath);
        createRestController(tableName,packagePath);
    }
}
