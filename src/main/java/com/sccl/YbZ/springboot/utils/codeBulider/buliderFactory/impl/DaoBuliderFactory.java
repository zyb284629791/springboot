package com.sccl.YbZ.springboot.utils.codeBulider.buliderFactory.impl;

import com.sccl.YbZ.springboot.utils.codeBulider.daoBulider.DaoBulider;
import com.sccl.YbZ.springboot.utils.codeBulider.buliderFactory.AbstractFactory;

/**
 * dao生成器工厂
 * Created by zyb on 2016/12/16.
 */
public class DaoBuliderFactory implements AbstractFactory{

    public DaoBulider getInstance(){
        DaoBulider callBack = new DaoBulider();
        return callBack;
    }
}
