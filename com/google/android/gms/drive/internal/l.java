// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.Metadata;

public final class l extends Metadata
{
    private final MetadataBundle Oj;
    
    public l(final MetadataBundle oj) {
        this.Oj = oj;
    }
    
    @Override
    protected <T> T a(final MetadataField<T> metadataField) {
        return this.Oj.a(metadataField);
    }
    
    public Metadata hR() {
        return new l(MetadataBundle.a(this.Oj));
    }
    
    @Override
    public boolean isDataValid() {
        return this.Oj != null;
    }
    
    @Override
    public String toString() {
        return "Metadata [mImpl=" + this.Oj + "]";
    }
}
