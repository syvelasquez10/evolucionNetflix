// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder$DriveFolderResult;

class u$e implements DriveFolder$DriveFolderResult
{
    private final Status CM;
    private final DriveFolder OR;
    
    public u$e(final Status cm, final DriveFolder or) {
        this.CM = cm;
        this.OR = or;
    }
    
    @Override
    public DriveFolder getDriveFolder() {
        return this.OR;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
