// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import java.util.Iterator;
import org.json.JSONArray;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.service.logging.JsonSerializer;

public class FalcorPathResult implements JsonSerializer
{
    public static final String PATH = "path";
    public static final String PATH_ERROR = "pathError";
    public static final String SUCCESS = "success";
    @SerializedName("path")
    @Since(1.0)
    private String path;
    @SerializedName("pathError")
    @Since(1.0)
    private List<DeepErrorElement> pathError;
    @SerializedName("success")
    @Since(1.0)
    private boolean success;
    
    public FalcorPathResult() {
        this.pathError = new ArrayList<DeepErrorElement>();
    }
    
    public FalcorPathResult(final String path, final boolean success, final List<DeepErrorElement> list) {
        this.pathError = new ArrayList<DeepErrorElement>();
        this.path = path;
        this.success = success;
        if (list != null) {
            this.pathError.addAll(list);
        }
    }
    
    public static FalcorPathResult createInstance(final String s) throws JSONException {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return createInstance(new JSONObject(s));
    }
    
    public static FalcorPathResult createInstance(final JSONObject jsonObject) throws JSONException {
        FalcorPathResult falcorPathResult;
        if (jsonObject == null) {
            falcorPathResult = null;
        }
        else {
            final FalcorPathResult falcorPathResult2 = new FalcorPathResult();
            falcorPathResult2.success = JsonUtils.getBoolean(jsonObject, "success", false);
            falcorPathResult2.path = JsonUtils.getString(jsonObject, "path", null);
            final JSONArray jsonArray = JsonUtils.getJSONArray(jsonObject, "pathError");
            falcorPathResult = falcorPathResult2;
            if (jsonArray != null) {
                int n = 0;
                while (true) {
                    falcorPathResult = falcorPathResult2;
                    if (n >= jsonArray.length()) {
                        break;
                    }
                    falcorPathResult2.pathError.add(DeepErrorElement.createInstance(jsonArray.getJSONObject(n)));
                    ++n;
                }
            }
        }
        return falcorPathResult;
    }
    
    public static String createJSONArray(final List<FalcorPathResult> list) throws JSONException {
        if (list == null || list.size() < 1) {
            return null;
        }
        final JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); ++i) {
            jsonArray.put((Object)list.get(i).toJSONObject());
        }
        return jsonArray.toString();
    }
    
    public static List<FalcorPathResult> createList(final String s) throws JSONException {
        List<FalcorPathResult> list;
        if (StringUtils.isEmpty(s)) {
            list = null;
        }
        else {
            final JSONArray jsonArray = new JSONArray(s);
            final ArrayList<FalcorPathResult> list2 = new ArrayList<FalcorPathResult>();
            int n = 0;
            while (true) {
                list = list2;
                if (n >= jsonArray.length()) {
                    break;
                }
                list2.add(createInstance(jsonArray.getJSONObject(n)));
                ++n;
            }
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
    public JSONObject toJSONObject() throws JSONException {
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
        return "FalcorPathResult [path=" + this.path + ", success=" + this.success + "]";
    }
}
