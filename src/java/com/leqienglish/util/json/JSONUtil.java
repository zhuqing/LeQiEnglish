package com.leqienglish.util.json;

import com.leqienglish.json.handler.JSONObjectHandler;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.leqienglish.util.FileUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {

    public static String errMessage() {
        JSONObject json = new JSONObject();
        json.put("msg", "error");
        return json.toString();
    }

    public static String okMessage() {
        return okJSONObject().toString();
    }
    
    public static JSONObject okJSONObject(){
        JSONObject json = new JSONObject();
        json.put("msg", "ok");
        return json;
    }

    /**
     * 布尔JSON
     *
     * @param bool
     * @return
     */
    public static String toBooleanJson(Boolean bool) {
        JSONObject json = new JSONObject();
        json.put("bool", bool);
        return json.toString();
    }

    /**
     * 生成jsonArray
     *
     * @param list
     * @return
     * @throws Exception
     */
    /**
     * 将Property文件转换为json
     *
     * @param propertyName
     * @return
     * @throws Exception
     */
    public static String property2Json(String propertyName) throws Exception {
        Properties properties = FileUtil.getProperties(propertyName);
        Set<Object> keySet = properties.keySet();
        JSONObject jsonObject = new JSONObject();
        for (Object key : keySet) {
            String value = properties.getProperty((String) key);
            jsonObject.put(key, value);

        }

        return jsonObject.toString();
    }

    public static JSONObject toJsonObject(Object t) {
        try {
            JSONObjectHandler handler = new JSONObjectHandler();
            return handler.toJSON(t);
        } catch (Exception e) {
            e.printStackTrace();

        }

        return new JSONObject();
    }

    public static JSONObject createJSONObject(JSONObject jsonObject, String key, Object value) {
        if (jsonObject == null) {
            jsonObject = new JSONObject();
        }
        jsonObject.put(key, value);
        return jsonObject;
    }
}
