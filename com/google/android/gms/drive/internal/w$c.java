// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveResource$MetadataResult;

class w$c implements DriveResource$MetadataResult
{
    private final Status CM;
    private final Metadata OV;
    
    public w$c(final Status cm, final Metadata ov) {
        this.CM = cm;
        this.OV = ov;
    }
    
    @Override
    public Metadata getMetadata() {
        return this.OV;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
