// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import org.json.JSONObject;

public class da implements cy
{
    private bq a;
    private bq b;
    
    public da(final bq a, final bq b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void a(final boolean b, int n, final JSONObject jsonObject) {
        if (b || (n >= 200 && n < 300)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            this.a.a();
            return;
        }
        this.a.a(this.b);
    }
}
