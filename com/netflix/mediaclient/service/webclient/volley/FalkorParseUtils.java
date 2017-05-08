// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import java.util.Locale;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.NetflixApplication;
import com.google.gson.Gson;
import java.util.Date;

public class FalkorParseUtils
{
    private static final Date EXPIRY_NEVER_DATE;
    public static final String FIELD_VALUE = "value";
    public static final String METHOD_TYPE_CALL = "call";
    public static final String METHOD_TYPE_GET = "get";
    private static final String TAG = "FalkorParseUtils";
    public static final String URL_PARAM_KEY_PARAM = "param";
    private static final Gson gson;
    
    static {
        EXPIRY_NEVER_DATE = new Date(System.currentTimeMillis() + 7776000000L);
        gson = NetflixApplication.getGson();
    }
    
    public static Date createDateFromExpires(final JsonElement jsonElement) {
        Date date2;
        final Date date = date2 = null;
        if (jsonElement != null) {
            date2 = date;
            if (jsonElement.isJsonObject()) {
                date2 = date;
                if (jsonElement.getAsJsonObject().has("$expires")) {
                    final long asLong = jsonElement.getAsJsonObject().get("$expires").getAsLong();
                    if (asLong < 0L) {
                        date2 = new Date(new Date().getTime() - asLong);
                    }
                    else {
                        if (asLong == 0L) {
                            return new Date(new Date().getTime() + 5000L);
                        }
                        if (asLong == 1L) {
                            return FalkorParseUtils.EXPIRY_NEVER_DATE;
                        }
                        return new Date(asLong);
                    }
                }
            }
        }
        return date2;
    }
    
    public static Object createObjectFromJson(final String s, final JsonElement jsonElement, final Class<?> clazz) {
        if (Log.isLoggable()) {
            Log.v(s, "Creating object from json of type: " + clazz);
        }
        try {
            return FalkorParseUtils.gson.fromJson(jsonElement, clazz);
        }
        catch (IncompatibleClassChangeError incompatibleClassChangeError) {
            Log.e("FalkorParseUtils", "spy-8880: IncompatibleClassChangeError - gson reflection fail", incompatibleClassChangeError);
            ErrorLoggingManager.logHandledException("spy-8880: IncompatibleClassChangeError - gson reflection fail");
            return null;
        }
    }
    
    public static JsonObject getDataObj(final String s, final String s2) {
        JsonObject asJsonObject;
        try {
            asJsonObject = new JsonParser().parse(s2).getAsJsonObject();
            if (hasErrors(asJsonObject) && Log.isLoggable()) {
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
            final JsonElement value = jsonObject.get(s);
            try {
                return FalkorParseUtils.gson.fromJson(value, clazz);
            }
            catch (IncompatibleClassChangeError incompatibleClassChangeError) {
                Log.e("FalkorParseUtils", "spy-8880: IncompatibleClassChangeError - gson reflection fail", incompatibleClassChangeError);
                ErrorLoggingManager.logHandledException("spy-8880: IncompatibleClassChangeError - gson reflection fail");
            }
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
