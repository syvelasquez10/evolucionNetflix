// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONException;
import org.json.JSONObject;

public final class ap implements t
{
    private boolean a;
    private boolean b;
    
    private ap(final boolean a) {
        this.a = a;
        this.b = true;
    }
    
    private JSONObject c() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("optOutStatus", this.a).put("optOutStatusSet", this.b);
            return jsonObject;
        }
        catch (JSONException ex) {
            return jsonObject;
        }
    }
    
    public final void a(final boolean a) {
        synchronized (this) {
            this.a = a;
        }
    }
    
    public final boolean a() {
        synchronized (this) {
            return this.a;
        }
    }
    
    public final boolean a(final h h, final String s, final String s2) {
        synchronized (this) {
            h.b(s, s2, this.c().toString());
            return true;
        }
    }
}
