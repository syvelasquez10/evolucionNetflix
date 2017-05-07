// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder$DriveFileResult;

class u$c implements DriveFolder$DriveFileResult
{
    private final Status CM;
    private final DriveFile OQ;
    
    public u$c(final Status cm, final DriveFile oq) {
        this.CM = cm;
        this.OQ = oq;
    }
    
    @Override
    public DriveFile getDriveFile() {
        return this.OQ;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
