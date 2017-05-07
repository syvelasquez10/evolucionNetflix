// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import com.google.android.gms.drive.metadata.a;

public class d extends a<Integer>
{
    public d(final String s, final int n) {
        super(s, n);
    }
    
    @Override
    protected void a(final Bundle bundle, final Integer n) {
        bundle.putInt(this.getName(), (int)n);
    }
    
    protected Integer f(final DataHolder dataHolder, final int n, final int n2) {
        return dataHolder.getInteger(this.getName(), n, n2);
    }
    
    protected Integer h(final Bundle bundle) {
        return bundle.getInt(this.getName());
    }
}
