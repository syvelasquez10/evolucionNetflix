// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata;

import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;

public final class StringMetadataField extends MetadataField<String>
{
    public StringMetadataField(final String s) {
        super(s);
    }
    
    @Override
    protected void a(final Bundle bundle, final String s) {
        bundle.putString(this.getName(), s);
    }
    
    protected String d(final DataHolder dataHolder, final int n, final int n2) {
        return dataHolder.getString(this.getName(), n, n2);
    }
    
    protected String f(final Bundle bundle) {
        return bundle.getString(this.getName());
    }
}
