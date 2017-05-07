// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONException;
import org.json.JSONObject;

public final class aq implements t
{
    public boolean a;
    public boolean b;
    public int c;
    public int d;
    public int e;
    public String f;
    public String g;
    
    private aq() {
        this.a = false;
        this.b = false;
        this.c = 0;
        this.d = 5;
        this.e = 5;
        this.f = "Would you mind taking a second to rate my app?  I would really appreciate it!";
        this.g = "Rate My App";
    }
    
    private aq(final aq aq) {
        this.a = aq.a;
        this.b = aq.b;
        this.c = aq.c;
        this.d = aq.d;
        this.e = aq.e;
        this.f = aq.f;
        this.g = aq.g;
    }
    
    private aq(final JSONObject jsonObject) {
        this.a = jsonObject.optBoolean("rateMyAppEnabled", false);
        this.b = jsonObject.optBoolean("hasRatedApp", false);
        this.c = jsonObject.optInt("numAppLoads", 0);
        this.d = jsonObject.optInt("rateAfterNumLoads", 5);
        this.e = jsonObject.optInt("remindAfterNumLoads", 5);
        this.f = jsonObject.optString("rateAppMessage", "Would you mind taking a second to rate my app?  I would really appreciate it!");
        this.g = jsonObject.optString("rateAppTitle", "Rate My App");
    }
    
    private JSONObject d() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("rateAfterNumLoads", this.d).put("remindAfterNumLoads", this.e).put("rateAppMessage", (Object)this.f).put("rateAppTitle", (Object)this.g).put("hasRatedApp", this.b).put("numAppLoads", this.c).put("rateMyAppEnabled", this.a);
            return jsonObject;
        }
        catch (JSONException ex) {
            return jsonObject;
        }
    }
    
    public final void a() {
        synchronized (this) {
            this.a = true;
        }
    }
    
    public final boolean a(final h h, final String s, final String s2) {
        synchronized (this) {
            h.b(s, s2, this.d().toString());
            return true;
        }
    }
    
    public final void c() {
        synchronized (this) {
            this.a = false;
        }
    }
}
