package com.sccl.YbZ.springboot.utils.codeBulider.buliderFactory.impl;

import com.sccl.YbZ.springboot.utils.codeBulider.daoBulider.DaoImplBulider;
import com.sccl.YbZ.springboot.utils.codeBulider.buliderFactory.AbstractFactory;

/**
 * daoImpl生成器工厂
 * Created by zyb on 2016/12/16.
 */
public class DaoImplBuliderFactory implements AbstractFactory{

    public DaoImplBulider getInstance(){
        DaoImplBulider callBack = new DaoImplBulider();
        return callBack;
    }
}
