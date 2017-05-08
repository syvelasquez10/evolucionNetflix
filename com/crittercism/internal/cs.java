// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.OutputStream;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;

public final class cs implements cw
{
    private Map a;
    
    public cs() {
        this.a = new HashMap();
    }
    
    private JSONArray a() {
        final JSONArray jsonArray = new JSONArray();
        for (final Map.Entry<K, cs$a> entry : this.a.entrySet()) {
            final JSONObject jsonObject = new JSONObject((Map)entry.getKey());
            final cs$a cs$a = entry.getValue();
            try {
                jsonArray.put((Object)new JSONObject().put("appLoads", (Object)jsonObject).put("count", cs$a.b).put("current", cs$a.a));
            }
            catch (JSONException ex) {}
        }
        return jsonArray;
    }
    
    @Override
    public final void a(final OutputStream outputStream) {
        outputStream.write(this.a().toString().getBytes("UTF8"));
    }
    
    @Override
    public final String toString() {
        try {
            return this.a().toString(4);
        }
        catch (JSONException ex) {
            dw.c("Unable to turn AppLoadRequest into a string", (Throwable)ex);
            return null;
        }
    }
}
