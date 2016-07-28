package com.leoman.utils;

import com.google.gson.*;
import com.leoman.utils.gson.DmsExclusionStrategy;

import com.leoman.velocity.entity.TableEntity;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by wangbin on 14-10-17.
 */
public class JsonUtil {

    protected final static Log logger = LogFactory.getLog(JsonUtil.class);

    public static GsonBuilder createGsonBuilder(){
        return new GsonBuilder().serializeNulls().
                setDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * json字符串转换成对象
     * @param str  json字符串
     * @param type 对象类型
     * @param <T>
     * @return
     */
    public static <T> T json2Obj(String str,Type type){
        Gson gson = createGsonBuilder().create();
        return gson.fromJson(str,type);
    }

    /**
     * json字符串转换成集合
     * @param str
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> json2List(String str, Class<T[]> type) {
        T[] list = createGsonBuilder().create().fromJson(str,type);
        return Arrays.asList(list);
    }

    public static String obj2Json(Object obj){
        Gson gson = createGsonBuilder().create();
        return  gson.toJson(obj);
    }

    /**
     * java对象转换成json字符串
     * @param obj  java对象
     * @param excludes 过滤字段
     * @return
     */
    public static String obj2Json(Object obj,String ... excludes ){
        ExclusionStrategy strategy = new DmsExclusionStrategy(excludes);
        Gson gson =createGsonBuilder()
                .setExclusionStrategies(strategy)
                .create();
        return  gson.toJson(obj);
    }

    /**
     * java对象转换成json字符串
     * @param obj
     * @param classes 类别数组
     * @param excludes  字符串数组
     * @return
     */
    public static String obj2Json(Object obj,Class[] classes,String... excludes){
        ExclusionStrategy strategy = new DmsExclusionStrategy(excludes,classes);
        Gson gson =createGsonBuilder()
                  .setExclusionStrategies(strategy)
                .create();
        return  gson.toJson(obj);
    }

    public static Boolean isGoodJson(String json){
        if (StringUtils.isBlank(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            logger.error("bad json: " + json);
            return false;
        }
    }


    /**
     * 将json格式的字符串解析成Map对象 <li>
     * json格式：{"name":"admin","retries":"3fff","testname" :"ddd","testretries":"fffffffff"}
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> jsontoMap(Object object) {
        Map<String, Object> data = new HashMap<String, Object>();
        // 将json字符串转换成jsonObject
        JSONObject jsonObject = JSONObject.fromObject(object);
        Iterator it = jsonObject.keys();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value = (Object) jsonObject.get(key);
            data.put(key, value);
        }
        return data;
    }






}
