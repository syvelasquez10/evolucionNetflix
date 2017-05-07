// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.wearable.c;
import com.google.android.gms.wearable.a;

public class kb implements a
{
    private int LF;
    private c adC;
    
    public kb(final a a) {
        this.LF = a.getType();
        this.adC = a.lZ().freeze();
    }
    
    @Override
    public int getType() {
        return this.LF;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public c lZ() {
        return this.adC;
    }
    
    public a me() {
        return this;
    }
}
