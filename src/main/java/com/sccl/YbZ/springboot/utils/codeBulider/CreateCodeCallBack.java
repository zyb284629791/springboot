package com.sccl.YbZ.springboot.utils.codeBulider;

/**
 * Created by zyb on 2016/12/13.
 */
public interface CreateCodeCallBack {

    /**
     * 根据模板生成代码
     * @param tableName 数据表名称
     * @return
     * @throws Exception
     */
    String createCode(String tableName) throws Exception ;

    /**
     * 要生成文件的文件名
     * @param tableName
     * @param packagePath
     * @return
     */
    String getFileName(String tableName,String packagePath);
}
