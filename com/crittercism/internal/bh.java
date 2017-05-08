// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;
import org.json.JSONArray;

public final class bh extends cg
{
    private String a;
    private String b;
    private int c;
    private String d;
    
    public bh(final int c, final String d) {
        this.a = ce.a.a();
        this.b = eb.a.a();
        this.c = c;
        this.d = d;
    }
    
    @Override
    public final JSONArray a() {
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("event", this.c - 1);
        hashMap.put("viewName", (Integer)this.d);
        return new JSONArray().put((Object)this.b).put(5).put((Object)new JSONObject((Map)hashMap));
    }
    
    @Override
    public final String e() {
        return this.a;
    }
}
