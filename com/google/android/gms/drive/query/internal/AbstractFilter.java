// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.query.c;
import com.google.android.gms.drive.query.Filter;

public abstract class AbstractFilter implements Filter
{
    @Override
    public String toString() {
        return String.format("Filter[%s]", this.a((f<Object>)new c()));
    }
}
