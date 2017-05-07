// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi$DriveIdResult;

class o$f implements DriveApi$DriveIdResult
{
    private final Status CM;
    private final DriveId MO;
    
    public o$f(final Status cm, final DriveId mo) {
        this.CM = cm;
        this.MO = mo;
    }
    
    @Override
    public DriveId getDriveId() {
        return this.MO;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
