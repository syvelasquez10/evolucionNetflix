// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.HashMap;
import com.google.android.gms.internal.d$a;
import java.util.Map;

public class cr$b
{
    private final Map<String, d$a> aqs;
    private d$a aqt;
    
    private cr$b() {
        this.aqs = new HashMap<String, d$a>();
    }
    
    public cr$b b(final String s, final d$a d$a) {
        this.aqs.put(s, d$a);
        return this;
    }
    
    public cr$b i(final d$a aqt) {
        this.aqt = aqt;
        return this;
    }
    
    public cr$a oU() {
        return new cr$a(this.aqs, this.aqt, null);
    }
}
