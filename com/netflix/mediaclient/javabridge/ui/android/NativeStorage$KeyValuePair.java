// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;

class NativeStorage$KeyValuePair
{
    public String key;
    final /* synthetic */ NativeStorage this$0;
    public String value;
    
    public NativeStorage$KeyValuePair(final NativeStorage this$0, final String key, final String value) {
        this.this$0 = this$0;
        this.key = key;
        this.value = value;
    }
    
    public NativeStorage$KeyValuePair(final NativeStorage this$0, final JSONObject jsonObject) {
        this.this$0 = this$0;
        this.key = JsonUtils.getString(jsonObject, "key", null);
        this.value = JsonUtils.getString(jsonObject, "value", null);
    }
    
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", (Object)this.key);
        jsonObject.put("value", (Object)this.value);
        return jsonObject;
    }
}
