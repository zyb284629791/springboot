package com.sccl.YbZ.springboot.biz.test.dao.impl;


import com.sccl.YbZ.springboot.common.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sccl.YbZ.springboot.biz.test.model.Test;
import com.sccl.YbZ.springboot.biz.test.dao.TestDao;

/**
* TestDao实现
* Created by Zhang on 2016.12.16.
*/
@Repository
public class TestDaoImpl extends BaseDaoImpl<Test,java.lang.String> implements TestDao{
}