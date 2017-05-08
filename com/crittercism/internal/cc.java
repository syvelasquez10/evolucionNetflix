// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;
import org.json.JSONArray;

public final class cc extends cg
{
    private String a;
    private String b;
    private int c;
    private String d;
    private String e;
    private String f;
    
    public cc(final int c) {
        if (c != cc$a.a) {
            final int b = cc$a.b;
        }
        this.a = ce.a.a();
        this.b = eb.a.a();
        this.c = c;
    }
    
    public cc(final int c, final String d) {
        if (c != cc$a.c) {
            final int d2 = cc$a.d;
        }
        this.a = ce.a.a();
        this.b = eb.a.a();
        this.c = c;
        this.d = d;
    }
    
    public cc(final int c, final String e, final String f) {
        final int e2 = cc$a.e;
        this.a = ce.a.a();
        this.b = eb.a.a();
        this.c = c;
        this.e = e;
        this.f = f;
    }
    
    @Override
    public final JSONArray a() {
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("change", this.c - 1);
        if (this.c == cc$a.c || this.c == cc$a.d) {
            hashMap.put("type", (Integer)this.d);
        }
        else if (this.c == cc$a.e) {
            hashMap.put("oldType", (Integer)this.e);
            hashMap.put("newType", (Integer)this.f);
        }
        return new JSONArray().put((Object)this.b).put(4).put((Object)new JSONObject((Map)hashMap));
    }
    
    @Override
    public final String e() {
        return this.a;
    }
}
