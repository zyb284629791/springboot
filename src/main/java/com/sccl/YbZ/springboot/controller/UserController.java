package com.sccl.YbZ.springboot.controller;

import com.sccl.YbZ.springboot.model.entity.User;
import com.sccl.YbZ.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* UserController
* Created by Zhang on 2016.12.13.
*/
@RestController
@RequestMapping("/userController")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "userService")
    UserService service;

    /**
    * insert
    */
    @RequestMapping(value = "/save/",method = RequestMethod.POST)
    public void save(@RequestBody User entity){
        service.save(entity);
    }

    /**
    * update
    */
    @RequestMapping("/update")
    public void update(User entity){
        service.update(entity);
    }

    /**
    * delete
    */
    @RequestMapping("/delete")
    public void delete(java.lang.Integer id){
        service.deleteById(id);
    }

    /**
    * findById
    * @param id
    * @return
    */
    @RequestMapping("/find/{id}")
    public User findById(@PathVariable java.lang.Integer id){
        User entity = service.findById(id);
        return entity;
    }
}