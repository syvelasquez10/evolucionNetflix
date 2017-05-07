// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import java.util.Date;
import com.google.android.gms.drive.metadata.OrderedMetadataField;

public class b extends OrderedMetadataField<Date>
{
    public b(final String s) {
        super(s);
    }
    
    @Override
    protected void a(final Bundle bundle, final Date date) {
        bundle.putLong(this.getName(), date.getTime());
    }
    
    protected Date f(final DataHolder dataHolder, final int n, final int n2) {
        return new Date(dataHolder.getLong(this.getName(), n, n2));
    }
    
    protected Date h(final Bundle bundle) {
        return new Date(bundle.getLong(this.getName()));
    }
}
