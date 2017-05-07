// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi$DriveContentsResult;
import com.google.android.gms.common.api.BaseImplementation$b;

class o$k extends c
{
    private final BaseImplementation$b<DriveApi$DriveContentsResult> De;
    
    public o$k(final BaseImplementation$b<DriveApi$DriveContentsResult> de) {
        this.De = de;
    }
    
    @Override
    public void a(final OnContentsResponse onContentsResponse) {
        this.De.b(new o$c(Status.Jo, new r(onContentsResponse.id())));
    }
    
    @Override
    public void o(final Status status) {
        this.De.b(new o$c(status, null));
    }
}
