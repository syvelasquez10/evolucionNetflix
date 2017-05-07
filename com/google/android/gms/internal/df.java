// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

@ez
public class df
{
    private final boolean rb;
    private final boolean rc;
    private final boolean rd;
    private final boolean re;
    private final boolean rf;
    
    private df(final df$a df$a) {
        this.rb = df$a.rb;
        this.rc = df$a.rc;
        this.rd = df$a.rd;
        this.re = df$a.re;
        this.rf = df$a.rf;
    }
    
    public JSONObject bL() {
        try {
            return new JSONObject().put("sms", this.rb).put("tel", this.rc).put("calendar", this.rd).put("storePicture", this.re).put("inlineVideo", this.rf);
        }
        catch (JSONException ex) {
            gs.b("Error occured while obtaining the MRAID capabilities.", (Throwable)ex);
            return null;
        }
    }
}
