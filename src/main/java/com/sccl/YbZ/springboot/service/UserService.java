package com.sccl.YbZ.springboot.service;

import com.sccl.YbZ.springboot.common.EntityManager;
import com.sccl.YbZ.springboot.model.entity.User;
import org.springframework.stereotype.Service;

/**
* UserService
* Created by Zhang on 2016.12.13.
*/
@Service("userService")
public class UserService extends EntityManager<User,java.lang.Integer> {
}