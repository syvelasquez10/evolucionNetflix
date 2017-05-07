// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import java.util.Iterator;
import org.json.JSONArray;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.service.logging.JsonSerializer;

public class FalkorPathResult implements JsonSerializer
{
    public static final String PATH = "path";
    public static final String PATH_ERROR = "pathError";
    public static final String SUCCESS = "success";
    @SerializedName("path")
    @Since(1.0)
    private String path;
    @SerializedName("pathError")
    @Since(1.0)
    private final List<DeepErrorElement> pathError;
    @SerializedName("success")
    @Since(1.0)
    private boolean success;
    
    public FalkorPathResult() {
        this.pathError = new ArrayList<DeepErrorElement>();
    }
    
    public FalkorPathResult(final String path, final boolean success, final List<DeepErrorElement> list) {
        this.pathError = new ArrayList<DeepErrorElement>();
        this.path = path;
        this.success = success;
        if (list != null) {
            this.pathError.addAll(list);
        }
    }
    
    public static FalkorPathResult createInstance(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return createInstance(new JSONObject(s));
    }
    
    public static FalkorPathResult createInstance(final JSONObject jsonObject) {
        int i = 0;
        if (jsonObject == null) {
            return null;
        }
        final FalkorPathResult falkorPathResult = new FalkorPathResult();
        falkorPathResult.success = JsonUtils.getBoolean(jsonObject, "success", false);
        falkorPathResult.path = JsonUtils.getString(jsonObject, "path", null);
        final JSONArray jsonArray = JsonUtils.getJSONArray(jsonObject, "pathError");
        if (jsonArray != null) {
            while (i < jsonArray.length()) {
                falkorPathResult.pathError.add(DeepErrorElement.createInstance(jsonArray.getJSONObject(i)));
                ++i;
            }
        }
        return falkorPathResult;
    }
    
    public static String createJSONArray(final List<FalkorPathResult> list) {
        if (list == null || list.size() < 1) {
            return null;
        }
        final JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); ++i) {
            jsonArray.put((Object)list.get(i).toJSONObject());
        }
        return jsonArray.toString();
    }
    
    public static List<FalkorPathResult> createList(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        final JSONArray jsonArray = new JSONArray(s);
        final ArrayList<FalkorPathResult> list = new ArrayList<FalkorPathResult>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(createInstance(jsonArray.getJSONObject(i)));
        }
        return list;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public List<DeepErrorElement> getPathError() {
        return this.pathError;
    }
    
    public boolean isSuccess() {
        return this.success;
    }
    
    @Override
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        if (this.path != null) {
            jsonObject.put("path", (Object)this.path);
        }
        jsonObject.put("success", this.success);
        if (this.pathError != null) {
            final JSONArray jsonArray = new JSONArray();
            jsonObject.put("pathError", (Object)jsonArray);
            final Iterator<DeepErrorElement> iterator = this.pathError.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().toJSONObject());
            }
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "FalkorPathResult [path=" + this.path + ", success=" + this.success + "]";
    }
}
