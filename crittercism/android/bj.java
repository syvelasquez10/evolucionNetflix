// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;
import org.json.JSONArray;

public final class bj extends ci
{
    private String a;
    private String b;
    private bj$a c;
    private String d;
    
    public bj(final bj$a c, final String d) {
        this.a = cg.a.a();
        this.b = ee.a.a();
        this.c = c;
        this.d = d;
    }
    
    @Override
    public final JSONArray a() {
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("event", this.c.ordinal());
        hashMap.put("viewName", (Integer)this.d);
        return new JSONArray().put((Object)this.b).put(5).put((Object)new JSONObject((Map)hashMap));
    }
    
    @Override
    public final String e() {
        return this.a;
    }
}
