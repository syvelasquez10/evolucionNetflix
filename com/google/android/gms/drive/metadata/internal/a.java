// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import com.google.android.gms.drive.metadata.MetadataField;

public class a extends MetadataField<Boolean>
{
    public a(final String s) {
        super(s);
    }
    
    @Override
    protected void a(final Bundle bundle, final Boolean b) {
        bundle.putBoolean(this.getName(), (boolean)b);
    }
    
    protected Boolean e(final DataHolder dataHolder, final int n, final int n2) {
        return dataHolder.getBoolean(this.getName(), n, n2);
    }
    
    protected Boolean g(final Bundle bundle) {
        return bundle.getBoolean(this.getName());
    }
}
