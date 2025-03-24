package org.kepler.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Serializations {
    public static final Gson gson = new GsonBuilder().create();

    private Serializations() {}

    public static <T> String makeJsonString(T type) {
        return (type instanceof String) ? type.toString() : gson.toJson(type);
    }

    public static <T> T makeClassType(String json, Type type) {
        return (type == String.class) ? (T) json : gson.fromJson(json, type);
    }

    public static Type makeListType(Class<?> cls) {
        return TypeToken.getParameterized(List.class, cls).getType();
    }

    public static <T> T getListObject(String json, Class<?> cls) {
        return gson.fromJson(json, makeListType(cls));
    }
}
