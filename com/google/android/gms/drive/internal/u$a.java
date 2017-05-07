// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder$DriveFileResult;
import com.google.android.gms.common.api.BaseImplementation$b;

class u$a extends c
{
    private final BaseImplementation$b<DriveFolder$DriveFileResult> De;
    
    public u$a(final BaseImplementation$b<DriveFolder$DriveFileResult> de) {
        this.De = de;
    }
    
    @Override
    public void a(final OnDriveIdResponse onDriveIdResponse) {
        this.De.b(new u$c(Status.Jo, new s(onDriveIdResponse.getDriveId())));
    }
    
    @Override
    public void o(final Status status) {
        this.De.b(new u$c(status, null));
    }
}
