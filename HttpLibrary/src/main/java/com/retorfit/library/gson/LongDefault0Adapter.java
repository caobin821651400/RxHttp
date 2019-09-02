package com.retorfit.library.gson;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 10:00
 * @Desc :定义为long类型,如果后台返回""或者null,则返回0
 * ====================================================
 */
public class LongDefault0Adapter implements JsonSerializer<Long>, JsonDeserializer<Long> {
    @Override
    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            if (json.getAsString().equals("") || json.getAsString().equals("null")) {
                return 0l;
            }
        } catch (Exception ignore) {
        }
        try {
            return json.getAsLong();
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}