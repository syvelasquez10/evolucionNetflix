// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi$MetadataBufferResult;

class o$h implements DriveApi$MetadataBufferResult
{
    private final Status CM;
    private final MetadataBuffer Oq;
    private final boolean Or;
    
    public o$h(final Status cm, final MetadataBuffer oq, final boolean or) {
        this.CM = cm;
        this.Oq = oq;
        this.Or = or;
    }
    
    @Override
    public MetadataBuffer getMetadataBuffer() {
        return this.Oq;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
