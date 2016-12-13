package com.sccl.YbZ.springboot.dao.impl;


import com.sccl.YbZ.springboot.common.BaseDaoImpl;
import com.sccl.YbZ.springboot.model.entity.User;
import com.sccl.YbZ.springboot.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
* UserDao实现
* Created by Zhang on 2016.12.13.
*/
@Repository
public class UserDaoImpl extends BaseDaoImpl<User,java.lang.Integer> implements UserDao{
}