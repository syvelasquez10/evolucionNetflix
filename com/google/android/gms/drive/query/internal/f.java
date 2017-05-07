// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import java.util.List;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.b;

public interface f<F>
{
     <T> F b(final b<T> p0, final T p1);
    
     <T> F b(final Operator p0, final MetadataField<T> p1, final T p2);
    
    F b(final Operator p0, final List<F> p1);
    
    F d(final MetadataField<?> p0);
    
     <T> F d(final MetadataField<T> p0, final T p1);
    
    F is();
    
    F j(final F p0);
}
