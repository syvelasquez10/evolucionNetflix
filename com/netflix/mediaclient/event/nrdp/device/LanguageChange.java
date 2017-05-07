// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.device;

import org.json.JSONObject;

public class LanguageChange extends BaseDeviceEvent
{
    private static final String ATTR_OLD = "old";
    public static final String TYPE = "languagechange";
    private String old;
    
    public LanguageChange(final String old) {
        super("languagechange");
        if (old == null) {
            throw new IllegalArgumentException("Old language is null!");
        }
        this.old = old;
    }
    
    @Override
    public JSONObject getData() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("old", (Object)this.old);
        jsonObject.put("type", (Object)this.getType());
        return jsonObject;
    }
}
