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
    
    private df(final a a) {
        this.rb = a.rb;
        this.rc = a.rc;
        this.rd = a.rd;
        this.re = a.re;
        this.rf = a.rf;
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
    
    public static final class a
    {
        private boolean rb;
        private boolean rc;
        private boolean rd;
        private boolean re;
        private boolean rf;
        
        public df bM() {
            return new df(this, null);
        }
        
        public a i(final boolean rb) {
            this.rb = rb;
            return this;
        }
        
        public a j(final boolean rc) {
            this.rc = rc;
            return this;
        }
        
        public a k(final boolean rd) {
            this.rd = rd;
            return this;
        }
        
        public a l(final boolean re) {
            this.re = re;
            return this;
        }
        
        public a m(final boolean rf) {
            this.rf = rf;
            return this;
        }
    }
}
