package com.sccl.YbZ.springboot.test;

import com.sccl.YbZ.springboot.utils.CodeBuliderUtil;

/**
 * Created by zyb on 2016/11/21.
 */
public class CodeBuliderTest {

    public static void main(String[] args) {
        CodeBuliderUtil.createModel("user","src/main/java/com/sccl/YbZ/springboot/biz/user/model/");
        CodeBuliderUtil.createDao("user","src/main/java/com/sccl/YbZ/springboot/biz/user/dao");
        CodeBuliderUtil.createDaoImpl("user","src/main/java/com/sccl/YbZ/springboot/biz/user/dao/impl");
        CodeBuliderUtil.createService("user","src/main/java/com/sccl/YbZ/springboot/biz/user/service");
        CodeBuliderUtil.createRestController("user","src/main/java/com/sccl/YbZ/springboot/biz/user/controller");
        System.out.println("done");
    }
}
