// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;

public class NccpSubtitle extends Subtitle
{
    public static final int IMPL_VALUE = 1;
    private static final String TRACK_TYPE_ASSISTIVE = "ASSISTIVE";
    private static final String TRACK_TYPE_PRIMARY = "PRIMARY";
    
    protected NccpSubtitle(final JSONObject jsonObject, final int nccpOrderNumber) throws JSONException {
        this.canDeviceRender = JsonUtils.getBoolean(jsonObject, "canDeviceRender", false);
        this.id = JsonUtils.getString(jsonObject, "id", null);
        this.languageCodeIso639_1 = JsonUtils.getString(jsonObject, "language", "en");
        this.languageDescription = JsonUtils.getString(jsonObject, "languageDescription", "English");
        this.nccpOrderNumber = nccpOrderNumber;
        final String string = JsonUtils.getString(jsonObject, "trackType", null);
        if (string == null) {
            this.trackType = -1;
        }
        else {
            if ("ASSISTIVE".equalsIgnoreCase(string)) {
                this.trackType = 1;
                return;
            }
            if ("PRIMARY".equalsIgnoreCase(string)) {
                this.trackType = 0;
            }
        }
    }
    
    public static final Subtitle newInstance(final JSONObject jsonObject, final int n) throws JSONException {
        return new NccpSubtitle(jsonObject, n);
    }
    
    @Override
    public JSONObject toJson() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("impl", 1);
        jsonObject.put("id", (Object)this.id);
        jsonObject.put("label", (Object)this.languageDescription);
        jsonObject.put("order", this.nccpOrderNumber);
        jsonObject.put("canDeviceRender", this.canDeviceRender);
        jsonObject.put("language", (Object)this.languageCodeIso639_1);
        jsonObject.put("languageDescription", (Object)this.languageDescription);
        Object o = null;
        if (this.trackType == 1) {
            o = "ASSISTIVE";
        }
        else if (this.trackType == 0) {
            o = "PRIMARY";
        }
        jsonObject.put("trackType", o);
        return jsonObject;
    }
}
