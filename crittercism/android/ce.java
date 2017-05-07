// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;
import org.json.JSONArray;

public final class ce extends ci
{
    private String a;
    private String b;
    private ce$a c;
    private String d;
    private String e;
    private String f;
    
    public ce(final ce$a c) {
        if (c != ce$a.a) {
            final ce$a b = ce$a.b;
        }
        this.a = cg.a.a();
        this.b = ee.a.a();
        this.c = c;
    }
    
    public ce(final ce$a c, final String d) {
        if (c != ce$a.c) {
            final ce$a d2 = ce$a.d;
        }
        this.a = cg.a.a();
        this.b = ee.a.a();
        this.c = c;
        this.d = d;
    }
    
    public ce(final ce$a c, final String e, final String f) {
        final ce$a e2 = ce$a.e;
        this.a = cg.a.a();
        this.b = ee.a.a();
        this.c = c;
        this.e = e;
        this.f = f;
    }
    
    @Override
    public final JSONArray a() {
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("change", this.c.ordinal());
        if (this.c == ce$a.c || this.c == ce$a.d) {
            hashMap.put("type", (Integer)this.d);
        }
        else if (this.c == ce$a.e) {
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
