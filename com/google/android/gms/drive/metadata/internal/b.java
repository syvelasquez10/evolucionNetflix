// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import java.util.Date;
import com.google.android.gms.drive.metadata.c;

public class b extends c<Date>
{
    public b(final String s, final int n) {
        super(s, n);
    }
    
    @Override
    protected void a(final Bundle bundle, final Date date) {
        bundle.putLong(this.getName(), date.getTime());
    }
    
    protected Date e(final DataHolder dataHolder, final int n, final int n2) {
        return new Date(dataHolder.getLong(this.getName(), n, n2));
    }
    
    protected Date g(final Bundle bundle) {
        return new Date(bundle.getLong(this.getName()));
    }
}
