// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.media.Subtitle;

public class NccpSubtitle extends Subtitle
{
    public static final int IMPL_VALUE = 1;
    private static final String TRACK_TYPE_ASSISTIVE = "ASSISTIVE";
    private static final String TRACK_TYPE_FORCED_NARRATIVE_SUBTITLE = "FORCED_NARRATIVE_SUBTITLE";
    private static final String TRACK_TYPE_PRIMARY = "PRIMARY";
    
    protected NccpSubtitle(final JSONObject jsonObject, final int nccpOrderNumber) {
        this.canDeviceRender = JsonUtils.getBoolean(jsonObject, "canDeviceRender", false);
        this.id = JsonUtils.getString(jsonObject, "id", null);
        this.languageCodeIso639_1 = JsonUtils.getString(jsonObject, "language", "en");
        this.languageDescription = JsonUtils.getString(jsonObject, "languageDescription", "English");
        this.nccpOrderNumber = nccpOrderNumber;
        final String string = JsonUtils.getString(jsonObject, "trackType", null);
        if (string == null) {
            this.trackType = 0;
        }
        else {
            if ("ASSISTIVE".equalsIgnoreCase(string)) {
                this.trackType = 2;
                return;
            }
            if ("PRIMARY".equalsIgnoreCase(string)) {
                this.trackType = 1;
                return;
            }
            if ("FORCED_NARRATIVE_SUBTITLE".equalsIgnoreCase(string)) {
                this.trackType = 6;
            }
        }
    }
    
    public static final Subtitle newInstance(final JSONObject jsonObject, final int n) {
        return new NccpSubtitle(jsonObject, n);
    }
    
    @Override
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("impl", 1);
        jsonObject.put("id", (Object)this.id);
        jsonObject.put("order", this.nccpOrderNumber);
        jsonObject.put("canDeviceRender", this.canDeviceRender);
        jsonObject.put("language", (Object)this.languageCodeIso639_1);
        jsonObject.put("languageDescription", (Object)this.languageDescription);
        Object o = null;
        if (this.trackType == 2) {
            o = "ASSISTIVE";
        }
        else if (this.trackType == 1) {
            o = "PRIMARY";
        }
        jsonObject.put("trackType", o);
        return jsonObject;
    }
}
