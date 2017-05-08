// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONArray;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import java.util.Collection;
import java.util.ArrayList;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import com.netflix.mediaclient.service.logging.JsonSerializer;

public class Error implements JsonSerializer
{
    public static final String DEEP_ERROR = "deepError";
    public static final String ROOT_CAUSE = "rootCause";
    @SerializedName("deepError")
    @Since(1.0)
    protected final List<DeepErrorElement> deepError;
    @SerializedName("rootCause")
    @Since(1.0)
    protected RootCause rootCause;
    
    public Error() {
        this.deepError = new ArrayList<DeepErrorElement>();
    }
    
    public Error(final RootCause rootCause, final List<DeepErrorElement> list) {
        this.deepError = new ArrayList<DeepErrorElement>();
        this.rootCause = rootCause;
        if (list != null) {
            this.deepError.addAll(list);
        }
    }
    
    public Error(final JSONObject jsonObject) {
        this.deepError = new ArrayList<DeepErrorElement>();
        final String string = JsonUtils.getString(jsonObject, "rootCause", (String)null);
        if (string != null) {
            this.rootCause = RootCause.valueOf(string);
        }
        final JSONArray jsonArray = JsonUtils.getJSONArray(jsonObject, "deepError");
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); ++i) {
                this.deepError.add(DeepErrorElement.createInstance(jsonArray.getJSONObject(i)));
            }
        }
    }
    
    public static Error createInstance(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        return new Error(jsonObject);
    }
    
    public static UIError createInstance(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return new UIError(new JSONObject(s));
    }
    
    public void addDeepError(final DeepErrorElement deepErrorElement) {
        this.deepError.add(deepErrorElement);
    }
    
    public void addDeepErrors(final List<DeepErrorElement> list) {
        this.deepError.addAll(list);
    }
    
    public List<DeepErrorElement> getDeepError() {
        return this.deepError;
    }
    
    public RootCause getRootCause() {
        return this.rootCause;
    }
    
    public void setRootCause(final RootCause rootCause) {
        this.rootCause = rootCause;
    }
    
    @Override
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        if (this.rootCause != null) {
            jsonObject.put("rootCause", (Object)this.rootCause.name());
        }
        if (this.deepError != null && this.deepError.size() > 0) {
            final JSONArray jsonArray = new JSONArray();
            final Iterator<DeepErrorElement> iterator = this.deepError.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().toJSONObject());
            }
            jsonObject.put("deepError", (Object)jsonArray);
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        try {
            return "Error: " + this.toJSONObject().toString();
        }
        catch (Throwable t) {
            return "Error [rootCause=" + this.rootCause + "]";
        }
    }
}
