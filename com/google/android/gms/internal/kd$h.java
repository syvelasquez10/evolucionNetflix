// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.b;

public class kd$h extends b implements SearchableMetadataField<Boolean>
{
    public kd$h(final String s, final int n) {
        super(s, n);
    }
    
    @Override
    protected Boolean e(final DataHolder dataHolder, final int n, final int n2) {
        return dataHolder.b(this.getName(), n, n2) != 0;
    }
}
