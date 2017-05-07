// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi$DriveContentsResult;

class o$c implements DriveApi$DriveContentsResult
{
    private final Status CM;
    private final DriveContents MT;
    
    public o$c(final Status cm, final DriveContents mt) {
        this.CM = cm;
        this.MT = mt;
    }
    
    @Override
    public DriveContents getDriveContents() {
        return this.MT;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
