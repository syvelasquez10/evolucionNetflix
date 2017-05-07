// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import java.util.Date;

public class d extends com.google.android.gms.drive.metadata.d<Date>
{
    public d(final String s, final int n) {
        super(s, n);
    }
    
    @Override
    protected void a(final Bundle bundle, final Date date) {
        bundle.putLong(this.getName(), date.getTime());
    }
    
    protected Date f(final DataHolder dataHolder, final int n, final int n2) {
        return new Date(dataHolder.a(this.getName(), n, n2));
    }
    
    protected Date i(final Bundle bundle) {
        return new Date(bundle.getLong(this.getName()));
    }
}
