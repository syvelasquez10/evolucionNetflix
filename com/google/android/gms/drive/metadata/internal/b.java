// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import com.google.android.gms.drive.metadata.a;

public class b extends a<Boolean>
{
    public b(final String s, final int n) {
        super(s, n);
    }
    
    @Override
    protected void a(final Bundle bundle, final Boolean b) {
        bundle.putBoolean(this.getName(), (boolean)b);
    }
    
    protected Boolean e(final DataHolder dataHolder, final int n, final int n2) {
        return dataHolder.d(this.getName(), n, n2);
    }
    
    protected Boolean h(final Bundle bundle) {
        return bundle.getBoolean(this.getName());
    }
}
