// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveResource$MetadataResult;

abstract class w$d extends p<DriveResource$MetadataResult>
{
    final /* synthetic */ w OT;
    
    private w$d(final w ot) {
        this.OT = ot;
    }
    
    public DriveResource$MetadataResult v(final Status status) {
        return new w$c(status, null);
    }
}
