// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder$DriveFolderResult;
import com.google.android.gms.common.api.BaseImplementation$b;

class u$b extends c
{
    private final BaseImplementation$b<DriveFolder$DriveFolderResult> De;
    
    public u$b(final BaseImplementation$b<DriveFolder$DriveFolderResult> de) {
        this.De = de;
    }
    
    @Override
    public void a(final OnDriveIdResponse onDriveIdResponse) {
        this.De.b(new u$e(Status.Jo, new u(onDriveIdResponse.getDriveId())));
    }
    
    @Override
    public void o(final Status status) {
        this.De.b(new u$e(status, null));
    }
}
