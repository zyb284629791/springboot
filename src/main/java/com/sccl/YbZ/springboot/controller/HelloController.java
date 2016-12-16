package com.sccl.YbZ.springboot.controller;

import com.sccl.YbZ.springboot.biz.user.model.User;
import com.sccl.YbZ.springboot.biz.user.service.UserService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyb on 2016/11/02.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Resource(name = "userService")
    UserService service;

    @RequestMapping("/index")
    public String hello(){
        logger.info("request index.... ");
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/find/{id}")
    public Object find(@PathVariable String id){
//        User user = service.findById(id);
//        return user;
        return null;
    }

    @RequestMapping("/findByPropertity")
    public Object findByPropertity(){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("sex",0);
        return service.findByPropertity(paramMap);
    }

    @RequestMapping("/findByPropertityWithOrderBy")
    public Object findByPropertityWithOrderBy(){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("sex",0);
        Map<String, Boolean> orderBy = new HashMap<>();
        orderBy.put("id", true);
        return service.findByPropertityWithOrderBy(paramMap,orderBy);
    }

    @RequestMapping("/update" )
    public String update(){
        User u = service.findById(2);
        u.setBirthday(new Date());
        service.update(u);
        return "success";
    }

    /**
     * save
     * @param user
     */
    @RequestMapping(value = "/save/",method = RequestMethod.POST)
    public void save(@RequestBody User user) {
        service.save(user);
    }

    @RequestMapping("/logs")
    public JSONObject log(){
        String log = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Zhang\\Desktop\\1.log")));
            log = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.fromObject(log);
        logger.info("size --- > " + jsonObject.getJSONArray("records").size());
        return jsonObject;
    }
}
