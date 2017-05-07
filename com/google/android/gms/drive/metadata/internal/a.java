// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;

public class a extends com.google.android.gms.drive.metadata.a<Boolean>
{
    public a(final String s, final int n) {
        super(s, n);
    }
    
    @Override
    protected void a(final Bundle bundle, final Boolean b) {
        bundle.putBoolean(this.getName(), (boolean)b);
    }
    
    protected Boolean d(final DataHolder dataHolder, final int n, final int n2) {
        return dataHolder.getBoolean(this.getName(), n, n2);
    }
    
    protected Boolean f(final Bundle bundle) {
        return bundle.getBoolean(this.getName());
    }
}
