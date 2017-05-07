// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata;

import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;

public abstract class CollectionMetadataField<T> extends MetadataField<Collection<T>>
{
    protected CollectionMetadataField(final String s) {
        super(s);
    }
    
    protected Collection<T> a(final DataHolder dataHolder, final int n, final int n2) {
        throw new UnsupportedOperationException("Cannot read collections from a dataHolder.");
    }
}
