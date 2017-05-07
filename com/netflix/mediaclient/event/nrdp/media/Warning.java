// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.util.JsonUtils;
import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Warning extends BaseMediaEvent
{
    public static final String ATTR_ERROR = "error";
    public static final String ATTR_ERRORCODE = "errorcode";
    public static final String ATTR_STACK = "stack";
    public static final String TYPE = "warning";
    public static final String WARNING_SUBTITLE_FAILURE = "NFErr_MC_SubtitleFailure";
    private int error;
    private JSONArray stack;
    
    public Warning(final JSONObject jsonObject) throws JSONException {
        super("warning", jsonObject);
    }
    
    public boolean containsInStack(final String s) {
        if (StringUtils.isEmpty(s) || this.stack == null || this.stack.length() < 1) {
            return false;
        }
        int n = 0;
        while (true) {
            Label_0080: {
                try {
                    if (n < this.stack.length()) {
                        final JSONObject jsonObject = this.stack.getJSONObject(n);
                        if (jsonObject == null) {
                            break Label_0080;
                        }
                        if (s.equals(BaseNccpEvent.getString(jsonObject, "errorcode", null))) {
                            return true;
                        }
                        break Label_0080;
                    }
                }
                catch (JSONException ex) {}
                break;
            }
            ++n;
        }
        return false;
    }
    
    public int getError() {
        return this.error;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.error = BaseNccpEvent.getInt(jsonObject, "error", 0);
        this.stack = JsonUtils.getJSONArray(jsonObject, "stack");
    }
}
