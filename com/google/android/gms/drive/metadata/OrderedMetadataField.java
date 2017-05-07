// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata;

import java.util.Collection;

public abstract class OrderedMetadataField<T extends Comparable<T>> extends MetadataField<T>
{
    protected OrderedMetadataField(final String s) {
        super(s);
    }
    
    protected OrderedMetadataField(final String s, final Collection<String> collection) {
        super(s, collection);
    }
}
