package com.sccl.YbZ.springboot.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by zyb on 2016/12/12.
 */
public class NullUtil {

    /**
     * 是否空集合
     * @param c
     * @return
     */
    public static boolean isNullCollection(Collection c){
        if(c != null && c.size() > 0)
            return false;
        return true;
    }

    /**
     * 是否非空集合
     * @param c
     * @return
     */
    public static boolean isNotNullCollection(Collection c){
        return !isNullCollection(c);
    }

    /**
     * 是否空map
     * @param map
     * @return
     */
    public static boolean isNullMap(Map map) {
        if (map != null && map.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 是否非空map
     * @param map
     * @return
     */
    public static boolean isNotNullMap(Map map) {
        return !isNullMap(map);
    }

    /**
     * 是否空字符串
     * @param string
     * @return
     */
    public static boolean isBlankString(String string) {
        return StringUtils.isBlank(string);
    }

    /**
     * 是否肥哦那个字符串
     * @param str
     * @return
     */
    public static boolean isNotBlankString(String str) {
        return StringUtils.isNotBlank(str);
    }
}
