package com.sccl.YbZ.springboot.biz.test.controller;

import com.sccl.YbZ.springboot.biz.test.model.Test;
import com.sccl.YbZ.springboot.biz.test.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* TestController
* Created by Zhang on 2016.12.16.
*/
@RestController
@RequestMapping("/testController")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "testService")
    TestService service;

    /**
    * insert
    */
    @RequestMapping(value = "/save/",method = RequestMethod.POST)
    public void save(@RequestBody Test entity){
        service.save(entity);
    }

    /**
    * update
    */
    @RequestMapping("/update")
    public void update(Test entity){
        service.update(entity);
    }

    /**
    * delete
    */
    @RequestMapping("/delete")
    public void delete(java.lang.String id){
        service.deleteById(id);
    }

    /**
    * findById
    * @param id
    * @return
    */
    @RequestMapping("/find/{id}")
    public Test findById(@PathVariable java.lang.String id){
        Test entity = service.findById(id);
        return entity;
    }
}