// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public final class bu implements bv
{
    private Map a;
    
    public bu() {
        this.a = new HashMap();
    }
    
    public final bu a(final bw bw) {
        if (bw.b() != null) {
            this.a.put(bw.a(), bw.b());
        }
        return this;
    }
    
    public final JSONObject a() {
        return new JSONObject(this.a);
    }
}
