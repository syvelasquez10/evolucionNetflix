// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi$ContentsResult;
import com.google.android.gms.common.api.BaseImplementation$b;

class o$j extends c
{
    private final BaseImplementation$b<DriveApi$ContentsResult> De;
    
    public o$j(final BaseImplementation$b<DriveApi$ContentsResult> de) {
        this.De = de;
    }
    
    @Override
    public void a(final OnContentsResponse onContentsResponse) {
        this.De.b(new o$a(Status.Jo, onContentsResponse.id()));
    }
    
    @Override
    public void o(final Status status) {
        this.De.b(new o$a(status, null));
    }
}
