package com.sccl.YbZ.springboot.utils.codeBulider.buliderFactory.impl;

import com.sccl.YbZ.springboot.utils.codeBulider.controllerBulider.RestControllerBulider;
import com.sccl.YbZ.springboot.utils.codeBulider.buliderFactory.AbstractFactory;

/**
 * restController生成器工厂
 * Created by zyb on 2016/12/16.
 */
public class RestControllerBuliderFactory implements AbstractFactory{

    public RestControllerBulider getInstance(){
        RestControllerBulider callBack = new RestControllerBulider();
        return callBack;
    }
}
