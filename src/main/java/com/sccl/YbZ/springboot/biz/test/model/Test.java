package com.sccl.YbZ.springboot.biz.test.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

/**
* test表对应实体
* Created by Zhang on 2016.12.16.
*/
@Entity
@Table(name="test")
public class Test implements java.io.Serializable {


    private java.lang.String id;

    private java.util.Date updateTime;
    private java.lang.String name;
    private java.lang.Integer deleteFlag;
    private java.util.Date createTime;


    @Id
    @Column(name="id",unique=true,nullable=false)
    public java.lang.String getId(){
        return id;
    }


    public void setId(java.lang.String id){
        this.id = id;
    }



    @Column(name="update_time")
    public java.util.Date getUpdateTime(){
        return updateTime;
    }


    public void setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime;
    }


    @Column(name="name")
    public java.lang.String getName(){
        return name;
    }


    public void setName(java.lang.String name){
        this.name = name;
    }


    @Column(name="delete_flag")
    public java.lang.Integer getDeleteFlag(){
        return deleteFlag;
    }


    public void setDeleteFlag(java.lang.Integer deleteFlag){
        this.deleteFlag = deleteFlag;
    }


    @Column(name="create_time")
    public java.util.Date getCreateTime(){
        return createTime;
    }


    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime;
    }


}