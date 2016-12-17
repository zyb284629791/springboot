package com.sccl.YbZ.springboot.biz.user.service;

import com.sccl.YbZ.springboot.common.EntityManager;
import org.springframework.stereotype.Service;
import com.sccl.YbZ.springboot.biz.user.model.User;

/**
* UserService
* Created by Zhang on 2016.12.16.
*/
@Service("userService")
public class UserService extends EntityManager<User,java.lang.Integer> {
}