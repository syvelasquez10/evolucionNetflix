// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import java.util.Locale;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.NetflixApplication;
import com.google.gson.Gson;

public class FalkorParseUtils
{
    private static final String FIELD_VALUE = "value";
    public static final String METHOD_TYPE_CALL = "call";
    public static final String METHOD_TYPE_GET = "get";
    public static final String URL_PARAM_KEY_PARAM = "param";
    private static final Gson gson;
    
    static {
        gson = NetflixApplication.getGson();
    }
    
    public static Object createObjectFromJson(final String s, final JsonElement jsonElement, final Class<?> clazz) {
        if (Log.isLoggable()) {
            Log.v(s, "Creating object from json of type: " + clazz);
        }
        return FalkorParseUtils.gson.fromJson(jsonElement, clazz);
    }
    
    public static JsonObject getDataObj(final String s, final String s2) {
        JsonObject asJsonObject;
        try {
            asJsonObject = new JsonParser().parse(s2).getAsJsonObject();
            if (hasErrors(asJsonObject)) {
                throw new FalkorException(getErrorMessage(asJsonObject, s));
            }
        }
        catch (Exception ex) {
            Log.v(s, "String response to parse = " + s2);
            throw new FalkorException("Error in creating JsonObject", ex);
        }
        return asJsonObject.getAsJsonObject("value");
    }
    
    public static String getErrorMessage(JsonObject asJsonObject, final String s) {
        if (hasErrors(asJsonObject)) {
            asJsonObject = asJsonObject.getAsJsonObject("error");
            if (Log.isLoggable()) {
                Log.d(s, "json error object: " + asJsonObject);
            }
            if (asJsonObject.has("message")) {
                return asJsonObject.get("message").toString();
            }
            if (asJsonObject.has("innerErrors")) {
                return asJsonObject.get("innerErrors").toString();
            }
        }
        return null;
    }
    
    public static Gson getGson() {
        return FalkorParseUtils.gson;
    }
    
    public static <T> T getPropertyObject(final JsonObject jsonObject, final String s, final Class<T> clazz) {
        if (jsonObject.has(s)) {
            return FalkorParseUtils.gson.fromJson(jsonObject.get(s), clazz);
        }
        return null;
    }
    
    public static boolean hasErrors(final JsonObject jsonObject) {
        return jsonObject.has("error") || jsonObject.has("innerErrors");
    }
    
    public static boolean isAlreadyInQueue(final String s) {
        return s != null && s.toLowerCase(Locale.US).contains("alreadyinqueue");
    }
    
    public static boolean isEmpty(final JsonObject jsonObject) {
        return jsonObject.isJsonNull() || jsonObject.toString().equals("{}");
    }
    
    public static boolean isNotInQueue(final String s) {
        return s != null && s.toLowerCase(Locale.US).contains("notinqueue");
    }
    
    public static boolean wasRequestNotValid(final String s) {
        return s != null && s.toLowerCase(Locale.US).contains("notvalidrequest");
    }
}
