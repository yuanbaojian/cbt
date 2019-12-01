package com.ybj.cbt.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public final class JacksonUtils {

    public static ObjectMapper objectMapper;


    /**   对象转String
     * @param object
     * @return java.lang.String
     * @author yuanbaojian
     * @date 2019/12/1
     * @time 22:56
     */
    public static String objectToJsonString(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**  string转jsonNode 或 jsonNode数组
     * @param jsonString
     * @return com.fasterxml.jackson.databind.JsonNode
     * @author yuanbaojian
     * @date 2019/12/1
     * @time 22:56
     */
    public static JsonNode stringToJsonNode(String jsonString){
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        }
        try {
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**  jsongString转Object（对象或对象数组）
     * @param jsonStr
     * @param valueType
     * @return T
     * @author yuanbaojian
     * @date 2019/12/1
     * @time 22:56
     */
    public static <T> T stringToObject(String jsonStr, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        }
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}