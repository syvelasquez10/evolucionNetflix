// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import com.google.android.gms.drive.metadata.a;

public class j extends a<String>
{
    public j(final String s, final int n) {
        super(s, n);
    }
    
    @Override
    protected void a(final Bundle bundle, final String s) {
        bundle.putString(this.getName(), s);
    }
    
    protected String h(final DataHolder dataHolder, final int n, final int n2) {
        return dataHolder.getString(this.getName(), n, n2);
    }
    
    protected String l(final Bundle bundle) {
        return bundle.getString(this.getName());
    }
}
