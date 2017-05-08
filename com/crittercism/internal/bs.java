// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public final class bs implements bt
{
    private Map a;
    
    public bs() {
        this.a = new HashMap();
    }
    
    public final bs a(final bu bu) {
        if (bu.b() != null) {
            this.a.put(bu.a(), bu.b());
        }
        return this;
    }
    
    public final JSONObject a() {
        return new JSONObject(this.a);
    }
}
