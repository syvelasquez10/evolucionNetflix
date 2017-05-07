// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

@ez
public class bq
{
    private u pw;
    private ah px;
    private JSONObject py;
    
    public bq(final u pw, final ah px, final JSONObject py) {
        this.pw = pw;
        this.px = px;
        this.py = py;
    }
    
    public void as() {
        this.pw.aj();
    }
    
    public void b(final String s, final int n) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("asset", n);
            jsonObject.put("template", (Object)s);
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("ad", (Object)this.py);
            jsonObject2.put("click", (Object)jsonObject);
            this.px.a("google.afma.nativeAds.handleClick", jsonObject2);
        }
        catch (JSONException ex) {
            gs.b("Unable to create click JSON.", (Throwable)ex);
        }
    }
}
