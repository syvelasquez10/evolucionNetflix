// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.OutputStream;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;

public final class cu implements cw
{
    public Map a;
    
    public cu(final ar ar) {
        (this.a = new HashMap()).put("app_id", ar.a());
        this.a.put("hashed_device_id", ar.c());
        this.a.put("library_version", "5.6.4");
    }
    
    public final cu a(final String s, final String s2) {
        this.a.put(s, s2);
        return this;
    }
    
    public final cu a(final String s, final JSONArray jsonArray) {
        this.a.put(s, jsonArray);
        return this;
    }
    
    @Override
    public final void a(final OutputStream outputStream) {
        dw.d("Writing to output stream");
        outputStream.write(new JSONObject(this.a).toString().getBytes("UTF8"));
    }
    
    @Override
    public final String toString() {
        try {
            return new JSONObject(this.a).toString(4);
        }
        catch (JSONException ex) {
            dw.c("Couldn't turn request into a string", (Throwable)ex);
            return null;
        }
    }
}
