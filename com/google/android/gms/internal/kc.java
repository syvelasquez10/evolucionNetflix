// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.wearable.c;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.a;
import com.google.android.gms.common.data.b;

public final class kc extends b implements a
{
    private final int LE;
    
    public kc(final DataHolder dataHolder, final int n, final int le) {
        super(dataHolder, n);
        this.LE = le;
    }
    
    @Override
    public int getType() {
        return this.getInteger("event_type");
    }
    
    @Override
    public c lZ() {
        return new kg(this.BB, this.BD, this.LE);
    }
    
    public a me() {
        return new kb(this);
    }
}
