package com.sccl.YbZ.springboot.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 拼写类型转换工具
 * Created by zyb on 2016/09/02.
 */
public class SpellUtil {

    /**
     * 转成帕斯卡拼写方法（每个单词首字母大写，没有连接符）
     * @param name
     * @return
     */
    public static String toPascalCase(String name) {
        StringBuilder result = new StringBuilder();
        boolean needUpper = false;
        if (StringUtils.isNotEmpty(name)) {
            result.append(Character.toUpperCase(name.charAt(0)));
            for (int i = 1; i < name.length(); i++) {
                String s = String.valueOf(name.charAt(i));
                if (s.equals("_")) {
                    needUpper = true;
                } else {
                    if (needUpper) {
                        result.append(s.toUpperCase());
                        needUpper = false;
                    } else {
                        result.append(s.toLowerCase());
                    }
                }
            }
        }
        return result.toString().intern();
    }


    /**
     * 转成驼峰表示法
     * @param name
     * @return
     */
    public static String toCamelCase(String name){
        StringBuilder result = new StringBuilder();
        boolean needUpper = false;
        if (StringUtils.isNotEmpty(name)) {
            result.append(name.charAt(0));
            for (int i = 1; i < name.length(); i++) {
                String s = String.valueOf(name.charAt(i));
                if (s.equals("_")) {
                    needUpper = true;
                } else {
                    if (needUpper) {
                        result.append(s.toUpperCase());
                        needUpper = false;
                    } else {
                        result.append(s.toLowerCase());
                    }
                }
            }
        }
        return result.toString().intern();
    }
}
