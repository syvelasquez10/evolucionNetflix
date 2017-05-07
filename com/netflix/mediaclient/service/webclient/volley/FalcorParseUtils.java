// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import java.util.Locale;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import java.util.Iterator;
import java.util.Map;
import com.google.gson.JsonParser;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.NetflixApplication;
import com.google.gson.Gson;

public class FalcorParseUtils
{
    private static final String FIELD_PATH = "path";
    private static final String FIELD_VALUE = "value";
    public static final String METHOD_TYPE_CALL = "call";
    public static final String METHOD_TYPE_GET = "get";
    public static final String URL_PARAM_KEY_PARAM = "param";
    private static final Gson gson;
    
    static {
        gson = NetflixApplication.getGson();
    }
    
    public static String buildEmptyValueMessage() {
        return new String("{\"value\":{},\"paths\":[[\"topGenres\",\"null\"]]}");
    }
    
    public static String buildErrorMessage() {
        return new String("{\"value\":{},\"error\":{\"message\":\"For input string: null\",\"pql\":[\"topGenre\",[\"null\"]]}}");
    }
    
    public static String buildInnerErrorMessage() {
        return new String("{ \"value\": {},  \"error\": { \"innerErrors\": [{\"message\": \"Not authorized.\",\"pql\": [\"topGenre\", [\"7462\"]],\"unauthorized\": true }, {\"message\": \"Not authorized.\",\"pql\": [\"topGenre\", [\"7462\"]], \"unauthorized\": true }]}}");
    }
    
    public static String buildMapErrorMessage() {
        return new String("");
    }
    
    public static String buildNotAuthorizedMessage() {
        return new String("{\"value\":{},\"error\":{\"message\":\"Not authorized.\",\"pql\":[\"topGenre\",[\"null\"]],\"unauthorized\":true}}");
    }
    
    public static String buildNullPointerMessage() {
        return new String("{\"value\":{},\"error\":{\"innerErrors\":[{\"error\":\"java.lang.NullPointerException\"},{\"error\":\"java.lang.NullPointerException\"}]}}");
    }
    
    public static boolean containsErrors(final JsonObject jsonObject) {
        return jsonObject.has("error") || jsonObject.has("innerErrors");
    }
    
    public static Object createObjectFromJson(final String s, final JsonElement jsonElement, final Class<?> clazz) {
        if (Log.isLoggable(s, 2)) {
            Log.v(s, "Creating object from json of type: " + clazz);
        }
        return FalcorParseUtils.gson.fromJson(jsonElement, clazz);
    }
    
    public static JsonObject getDataObj(final String s, final String s2) {
        JsonObject asJsonObject;
        try {
            asJsonObject = new JsonParser().parse(s2).getAsJsonObject();
            if (containsErrors(asJsonObject)) {
                throw new FalcorServerException(getErrorMessage(asJsonObject));
            }
        }
        catch (Exception ex) {
            Log.v(s, "String response to parse = " + s2);
            throw new FalcorParseException("Error in creating JsonObject", ex);
        }
        return asJsonObject.getAsJsonObject("value");
    }
    
    public static String getErrorMessage(JsonObject asJsonObject) {
        if (containsErrors(asJsonObject)) {
            asJsonObject = asJsonObject.getAsJsonObject("error");
            if (asJsonObject.has("message")) {
                return asJsonObject.get("message").toString();
            }
            if (asJsonObject.has("innerErrors")) {
                return asJsonObject.get("innerErrors").toString();
            }
        }
        return null;
    }
    
    public static JsonObject getFirstJsonObject(JsonObject asJsonObject) {
        if (asJsonObject == null) {
            return null;
        }
        final Iterator<Map.Entry<String, JsonElement>> iterator = asJsonObject.entrySet().iterator();
        JsonElement jsonElement;
        if (iterator.hasNext()) {
            jsonElement = ((Map.Entry<String, JsonElement>)iterator.next()).getValue();
        }
        else {
            jsonElement = null;
        }
        if (jsonElement != null) {
            asJsonObject = jsonElement.getAsJsonObject();
        }
        else {
            asJsonObject = null;
        }
        return asJsonObject;
    }
    
    public static Gson getGson() {
        return FalcorParseUtils.gson;
    }
    
    public static String getIdFromPath(final String s, final JsonObject jsonObject) {
        try {
            return jsonObject.getAsJsonArray("path").get(1).getAsString();
        }
        catch (Exception ex) {
            Log.v(s, "String jsonObj to parse = " + jsonObject.toString());
            throw new FalcorParseException("Missing path/id in jsonObj", ex);
        }
    }
    
    public static <T> T getPropertyObject(final JsonObject jsonObject, final String s, final Class<T> clazz) {
        if (jsonObject.has(s)) {
            return FalcorParseUtils.gson.fromJson(jsonObject.get(s), clazz);
        }
        return null;
    }
    
    public static VideoType getTypeFromPath(final String s, final JsonObject jsonObject) {
        try {
            return VideoType.create(jsonObject.getAsJsonArray("path").get(0).getAsString());
        }
        catch (Exception ex) {
            Log.v(s, "String jsonObj to parse = " + jsonObject.toString());
            throw new FalcorParseException("Missing path/type in jsonObj", ex);
        }
    }
    
    public static boolean isAlreadyInQueue(final String s) {
        return s != null && s.toLowerCase(Locale.US).contains("alreadyinqueue");
    }
    
    public static boolean isDeletedProfile(final String s) {
        return s.toLowerCase(Locale.US).contains("deleted profile");
    }
    
    public static boolean isEmpty(final JsonObject jsonObject) {
        return jsonObject.isJsonNull() || jsonObject.toString().equals("{}");
    }
    
    public static boolean isInvalidCounty(final String s) {
        return s.toLowerCase(Locale.US).contains("failurereason=invalidcountry");
    }
    
    public static boolean isMapCacheError(final String s) {
        return s.contains("cache miss") || s.contains("mapgetcachedlistclient failed") || s.contains("cachemiss");
    }
    
    public static boolean isMapError(final String s) {
        return s.toLowerCase(Locale.US).contains("map error");
    }
    
    public static boolean isNotAuthorized(final String s) {
        return s.toLowerCase(Locale.US).contains("not authorized") || s.toLowerCase(Locale.US).contains("unauthorized");
    }
    
    public static boolean isNotInQueue(final String s) {
        return s.toLowerCase(Locale.US).contains("notinqueue");
    }
    
    public static boolean isNullPointerException(final String s) {
        return s.toLowerCase(Locale.US).contains("nullpointerexception");
    }
    
    public static boolean isWrongState(final String s) {
        return s.contains("wrong state");
    }
    
    public static boolean wasRequestNotValid(final String s) {
        return s != null && s.toLowerCase(Locale.US).contains("notvalidrequest");
    }
}
