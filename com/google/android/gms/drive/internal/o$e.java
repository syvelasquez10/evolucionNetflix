// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi$DriveIdResult;
import com.google.android.gms.common.api.BaseImplementation$b;

class o$e extends c
{
    private final BaseImplementation$b<DriveApi$DriveIdResult> De;
    
    public o$e(final BaseImplementation$b<DriveApi$DriveIdResult> de) {
        this.De = de;
    }
    
    @Override
    public void a(final OnDriveIdResponse onDriveIdResponse) {
        this.De.b(new o$f(Status.Jo, onDriveIdResponse.getDriveId()));
    }
    
    @Override
    public void a(final OnMetadataResponse onMetadataResponse) {
        this.De.b(new o$f(Status.Jo, new l(onMetadataResponse.il()).getDriveId()));
    }
    
    @Override
    public void o(final Status status) {
        this.De.b(new o$f(status, null));
    }
}
