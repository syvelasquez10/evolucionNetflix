// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;
import org.json.JSONArray;

public final class bw extends cg
{
    private String a;
    private String b;
    private String c;
    private String d;
    
    public bw(final String c, final String d) {
        this.a = ce.a.a();
        this.b = eb.a.a();
        this.c = c;
        this.d = d;
    }
    
    @Override
    public final JSONArray a() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", this.c);
        hashMap.put("reason", this.d);
        return new JSONArray().put((Object)this.b).put(6).put((Object)new JSONObject((Map)hashMap));
    }
    
    @Override
    public final String e() {
        return this.a;
    }
}
