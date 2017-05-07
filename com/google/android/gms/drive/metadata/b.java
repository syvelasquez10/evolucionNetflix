// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata;

import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;

public abstract class b<T> extends a<Collection<T>>
{
    protected b(final String s, final int n) {
        super(s, n);
    }
    
    protected Collection<T> c(final DataHolder dataHolder, final int n, final int n2) {
        throw new UnsupportedOperationException("Cannot read collections from a dataHolder.");
    }
}
