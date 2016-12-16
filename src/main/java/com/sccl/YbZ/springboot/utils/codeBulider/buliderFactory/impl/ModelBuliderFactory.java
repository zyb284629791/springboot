package com.sccl.YbZ.springboot.utils.codeBulider.buliderFactory.impl;

import com.sccl.YbZ.springboot.utils.codeBulider.buliderFactory.AbstractFactory;
import com.sccl.YbZ.springboot.utils.codeBulider.modelBulider.ModelBulider;

/**
 * model生成器工厂
 * Created by zyb on 2016/12/16.
 */
public class ModelBuliderFactory implements AbstractFactory{

    public ModelBulider getInstance(){
        ModelBulider callBack = new ModelBulider();
        return callBack;
    }
}
