package com.sccl.YbZ.springboot.utils.codeBulider.buliderFactory.impl;

import com.sccl.YbZ.springboot.utils.codeBulider.buliderFactory.AbstractFactory;
import com.sccl.YbZ.springboot.utils.codeBulider.serviceBulider.ServiceBulider;

/**
 * service生成器工厂
 * Created by zyb on 2016/12/16.
 */
public class ServiceBuliderFactory implements AbstractFactory{

    public ServiceBulider getInstance(){
        ServiceBulider callBack = new ServiceBulider();
        return callBack;
    }
}
