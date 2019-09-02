package com.retorfit.library.gson;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 9:57
 * @Desc :定义为double类型,如果后台返回""或者null,则返回0.00
 * ====================================================
 */
public class DoubleDefault0Adapter implements JsonSerializer<Double>, JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json.getAsString().equals("") || json.getAsString().equals("null")) {
                return 0.00;
            }
        } catch (Exception ignore) {
        }
        try {
            return json.getAsDouble();
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}