package com.xiaoju.ibt.merchant.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * json工具类
 */
public enum JsonUtil {
    INSTANCE;

    private final ObjectMapper objectMapper = new ObjectMapper();

    JsonUtil() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                //时间类型
                .registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     * json转object
     *
     * @param json  json字符串
     * @param clazz 被转对象的类型
     * @param <T>   通配符
     * @return string
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        return INSTANCE.asObject(json, clazz);
    }

    /**
     * 对象转json
     *
     * @param object 被转换的对象
     * @return 对象的json字符串
     */
    public static String toString(Object object) {
        return INSTANCE.asString(object);
    }

    /**
     * 对象转json
     *
     * @param object 被转换的对象
     * @return 对象的json字符串
     */
    public String asString(Object object) {
        if (object == null) {
            return "";
        }
        if (object instanceof String) {
            return (String) object;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json转object
     *
     * @param json  json字符串
     * @param clazz 被转对象的类型
     * @param <T>   通配符
     * @return string
     */
    public <T> T asObject(String json, Class<T> clazz) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            }

            if (clazz == String.class) {
                return (T) json;
            }
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
