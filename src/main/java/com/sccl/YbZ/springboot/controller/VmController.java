package com.sccl.YbZ.springboot.controller;

import com.google.common.collect.Lists;
import com.sccl.YbZ.springboot.model.codeBulider.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zyb on 2016/11/11.
 */
@Controller
@RequestMapping("/vm")
public class VmController {

    private Logger logger = LoggerFactory.getLogger(VmController.class);

    @RequestMapping("/hello")
    public String hello(Model model){
        logger.info("request hello.... ");
        List<String> l= Lists.newArrayList();
        l.add("哈喽，hadoop");
        l.add("哈喽，hbase");
        l.add("哈喽，hive");
        l.add("哈喽，pig");
        l.add("哈喽，zookeeper");
        l.add("哈喽，三劫散仙");
        //将数据存放map里面，可以直接在velocity页面，使用key访问
        model.addAttribute("data",l);
        logger.info("model data ready");
        return "views/hello";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        List<TableInfo> list = Lists.newArrayList();
        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableName("user");
        TableInfo tableInfo1 = new TableInfo();
        tableInfo1.setTableName("test");
        list.add(tableInfo);
        list.add(tableInfo1);
        model.addAttribute("lists", list);
        return "views/index";
    }
}
