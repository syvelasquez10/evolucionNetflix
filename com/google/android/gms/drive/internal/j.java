// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.Metadata;

public final class j extends Metadata
{
    private final MetadataBundle ED;
    
    public j(final MetadataBundle ed) {
        this.ED = ed;
    }
    
    @Override
    protected <T> T a(final MetadataField<T> metadataField) {
        return this.ED.a(metadataField);
    }
    
    public Metadata fB() {
        return new j(MetadataBundle.a(this.ED));
    }
    
    @Override
    public boolean isDataValid() {
        return this.ED != null;
    }
    
    @Override
    public String toString() {
        return "Metadata [mImpl=" + this.ED + "]";
    }
}
