// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;
import org.json.JSONArray;

public final class bj extends cg
{
    private String a;
    private String b;
    private bj$a c;
    
    public bj(final bj$a c) {
        this.a = ce.a.a();
        this.b = eb.a.a();
        this.c = c;
    }
    
    @Override
    public final JSONArray a() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", this.c.c);
        return new JSONArray().put((Object)this.b).put(3).put((Object)new JSONObject((Map)hashMap));
    }
    
    @Override
    public final String e() {
        return this.a;
    }
}
