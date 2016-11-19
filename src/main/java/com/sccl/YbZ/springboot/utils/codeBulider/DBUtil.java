package com.sccl.YbZ.springboot.utils.codeBulider;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.util.Properties;

/**
 * Created by zyb on 2016/11/16.
 */
public class DBUtil {

    private static String driverClassName;
    private static String url;
    private static String dbUsername;
    private static String dbPassword;
    private static DriverManagerDataSource dataSource;

    private static void initConfig(){
        InputStream in = null;
        try {
//            String baseDir = System.getProperty("user.dir");
            in = new BufferedInputStream(DBUtil.class.getResourceAsStream("/application.properties"));
            Properties p = new Properties();
            p.load(in);
            driverClassName = p.getProperty("spring.datasource.driver-class-name");
            url = p.getProperty("spring.datasource.url");
            dbUsername = p.getProperty("spring.datasource.username");
            dbPassword = p.getProperty("spring.datasource.password");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static DataSource getDataSource(){
        if(dataSource == null){
            dataSource = new DriverManagerDataSource();
            initConfig();
        }
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
}
