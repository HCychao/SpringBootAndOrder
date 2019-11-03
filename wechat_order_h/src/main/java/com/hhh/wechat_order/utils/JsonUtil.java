package com.hhh.wechat_order.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * json 格式化工具
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
