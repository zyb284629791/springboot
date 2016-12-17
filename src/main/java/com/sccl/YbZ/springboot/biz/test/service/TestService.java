package com.sccl.YbZ.springboot.biz.test.service;

import com.sccl.YbZ.springboot.common.EntityManager;
import org.springframework.stereotype.Service;
import com.sccl.YbZ.springboot.biz.test.model.Test;

/**
* TestService
* Created by Zhang on 2016.12.16.
*/
@Service("testService")
public class TestService extends EntityManager<Test,java.lang.String> {
}