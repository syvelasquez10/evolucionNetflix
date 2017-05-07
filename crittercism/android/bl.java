// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;
import org.json.JSONArray;

public final class bl extends ci
{
    private String a;
    private String b;
    private bl$a c;
    
    public bl(final bl$a c) {
        this.a = cg.a.a();
        this.b = ee.a.a();
        this.c = c;
    }
    
    @Override
    public final JSONArray a() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", this.c.a());
        return new JSONArray().put((Object)this.b).put(3).put((Object)new JSONObject((Map)hashMap));
    }
    
    @Override
    public final String e() {
        return this.a;
    }
}
