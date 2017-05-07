// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class b extends Metadata
{
    private final MetadataBundle qN;
    
    public b(final MetadataBundle qn) {
        this.qN = qn;
    }
    
    @Override
    protected <T> T a(final MetadataField<T> metadataField) {
        return this.qN.a(metadataField);
    }
    
    public Metadata cK() {
        return new b(MetadataBundle.a(this.qN));
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
