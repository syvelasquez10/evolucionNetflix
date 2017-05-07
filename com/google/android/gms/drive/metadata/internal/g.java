// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import com.google.android.gms.drive.metadata.a;

public class g extends a<Long>
{
    public g(final String s, final int n) {
        super(s, n);
    }
    
    @Override
    protected void a(final Bundle bundle, final Long n) {
        bundle.putLong(this.getName(), (long)n);
    }
    
    protected Long h(final DataHolder dataHolder, final int n, final int n2) {
        return dataHolder.a(this.getName(), n, n2);
    }
    
    protected Long k(final Bundle bundle) {
        return bundle.getLong(this.getName());
    }
}
