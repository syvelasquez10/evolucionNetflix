// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata;

import java.util.Collection;
import android.os.Bundle;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.data.DataHolder;

public interface MetadataField<T>
{
    T a(final DataHolder p0, final int p1, final int p2);
    
    void a(final DataHolder p0, final MetadataBundle p1, final int p2, final int p3);
    
    void a(final T p0, final Bundle p1);
    
    T d(final Bundle p0);
    
    Collection<String> fR();
    
    String getName();
}
