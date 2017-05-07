// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Collections;
import com.google.android.gms.internal.d$a;
import java.util.Map;

public class cr$a
{
    private final Map<String, d$a> aqs;
    private final d$a aqt;
    
    private cr$a(final Map<String, d$a> aqs, final d$a aqt) {
        this.aqs = aqs;
        this.aqt = aqt;
    }
    
    public static cr$b oR() {
        return new cr$b(null);
    }
    
    public void a(final String s, final d$a d$a) {
        this.aqs.put(s, d$a);
    }
    
    public Map<String, d$a> oS() {
        return Collections.unmodifiableMap((Map<? extends String, ? extends d$a>)this.aqs);
    }
    
    public d$a oT() {
        return this.aqt;
    }
    
    @Override
    public String toString() {
        return "Properties: " + this.oS() + " pushAfterEvaluate: " + this.aqt;
    }
}
