package com.mola.core.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Json处理工具
 *
 * @author hatim
 */
public final class JsonHelper {
    private static ObjectMapper objectMapper = null;

    public static ObjectMapper getInstance() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        }
        return objectMapper;
    }

    public static String toJson(Object obj) throws JsonProcessingException {
        return getInstance().writeValueAsString(obj);
    }

    /**
     * json string convert to javaBean
     *
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T fromJson(String jsonStr, Class<T> clazz)
            throws Exception {
        if (StringHelper.isBlank(jsonStr)) {
            return null;
        }
        return getInstance().readValue(jsonStr, clazz);
    }

    /**
     * json string convert to javaBean list
     *
     * @param jsonStr
     * @param javaType
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T fromJson(String jsonStr, JavaType javaType)
            throws Exception {
        return getInstance().readValue(jsonStr, javaType);
    }

    /**
     * json string convert to map
     *
     * @param jsonStr
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> Map<String, Object> jsonToMap(String jsonStr)
            throws Exception {
        return getInstance().readValue(jsonStr, Map.class);
    }

    /**
     * json string convert to map with javaBean
     *
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> Map<String, T> jsonToMap(String jsonStr, Class<T> clazz)
            throws Exception {
        Map<String, Map<String, Object>> map = (Map<String, Map<String, Object>>) getInstance().readValue(jsonStr,
                new TypeReference<Map<String, T>>() {
                });
        Map<String, T> result = new HashMap<>();
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), mapToBean(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * json array string convert to list with javaBean
     *
     * @param jsonArrayStr
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> jsonToList(String jsonArrayStr, Class<T> clazz)
            throws Exception {
        List<Map<String, Object>> list = (List<Map<String, Object>>) getInstance().readValue(jsonArrayStr,
                new TypeReference<List<T>>() {
                });
        List<T> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            result.add(mapToBean(map, clazz));
        }
        return result;
    }

    /**
     * map convert to javaBean
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map map, Class<T> clazz) {
        return getInstance().convertValue(map, clazz);
    }

    /**
     * 获取JavaType
     *
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return getInstance().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 从json串获取字段值
     *
     * @param jsonStr
     * @param key
     * @return
     */
    @Deprecated
    public static String getValueByKey(String jsonStr, String key) throws IOException {
        if (StringHelper.isBlank(jsonStr) || StringHelper.isBlank(key)) {
            return null;
        }
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        String[] keys = key.split("\\.");
        for (String s : keys) {
            jsonNode = jsonNode.get(s);
        }
        return jsonNode.asText();
    }

    /**
     * 从json串获取字段值 加强版
     *
     * @param jsonStr
     * @param key
     * @return
     */
    public static List<String> getValueByKeyPro(String jsonStr, String key) throws IOException {
        List<String> values = new ArrayList<>();
        if (StringHelper.isBlank(jsonStr) || StringHelper.isBlank(key)) {
            return null;
        }
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        String[] keys = key.split("\\.");

        if (jsonNode instanceof ArrayNode) {
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            for (JsonNode node : arrayNode) {
                for (String s : keys) {
                    node = node.get(s);
                }
                values.add(node.asText());
            }
        } else if (jsonNode instanceof ObjectNode) {
            for (String s : keys) {
                jsonNode = jsonNode.get(s);
            }
            values.add(jsonNode.asText());
        }
        return values;
    }
}
