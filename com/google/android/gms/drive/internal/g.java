// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.Metadata;

public final class g extends Metadata
{
    private final MetadataBundle qN;
    
    public g(final MetadataBundle qn) {
        this.qN = qn;
    }
    
    @Override
    protected <T> T a(final MetadataField<T> metadataField) {
        return this.qN.a(metadataField);
    }
    
    public Metadata cK() {
        return new g(MetadataBundle.a(this.qN));
    }
    
    @Override
    public boolean isDataValid() {
        return this.qN != null;
    }
    
    @Override
    public String toString() {
        return "Metadata [mImpl=" + this.qN + "]";
    }
}
