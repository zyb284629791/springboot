package com.sccl.YbZ.springboot.biz.user.dao.impl;


import com.sccl.YbZ.springboot.common.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sccl.YbZ.springboot.biz.user.model.User;
import com.sccl.YbZ.springboot.biz.user.dao.UserDao;

/**
* UserDao实现
* Created by Zhang on 2016.12.16.
*/
@Repository
public class UserDaoImpl extends BaseDaoImpl<User,java.lang.Integer> implements UserDao{
}