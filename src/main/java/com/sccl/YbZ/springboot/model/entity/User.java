package com.sccl.YbZ.springboot.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

/**
* user表对应实体
* Created by Zhang on 2016.12.13.
*/
@Entity
@Table(name="user")
public class User implements java.io.Serializable {

    //public String tableName = "user";

    private java.lang.Integer id;

    private java.util.Date birthday;
    private java.lang.String sex;
    private java.lang.String username;
    private java.lang.String address;
    private java.lang.String createTime;


    @Id
    @Column(name="id",unique=true,nullable=false)
    public java.lang.Integer getId(){
        return id;
    }


    public void setId(java.lang.Integer id){
        this.id = id;
    }



    @Column(name="birthday")
    public java.util.Date getBirthday(){
        return birthday;
    }


    public void setBirthday(java.util.Date birthday){
        this.birthday = birthday;
    }


    @Column(name="sex")
    public java.lang.String getSex(){
        return sex;
    }


    public void setSex(java.lang.String sex){
        this.sex = sex;
    }


    @Column(name="username")
    public java.lang.String getUsername(){
        return username;
    }


    public void setUsername(java.lang.String username){
        this.username = username;
    }


    @Column(name="address")
    public java.lang.String getAddress(){
        return address;
    }


    public void setAddress(java.lang.String address){
        this.address = address;
    }


    @Column(name="create_time")
    public java.lang.String getCreateTime(){
        return createTime;
    }


    public void setCreateTime(java.lang.String createTime){
        this.createTime = createTime;
    }


}