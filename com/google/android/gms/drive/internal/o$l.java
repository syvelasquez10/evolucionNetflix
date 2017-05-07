// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi$MetadataBufferResult;
import com.google.android.gms.common.api.BaseImplementation$b;

class o$l extends c
{
    private final BaseImplementation$b<DriveApi$MetadataBufferResult> De;
    
    public o$l(final BaseImplementation$b<DriveApi$MetadataBufferResult> de) {
        this.De = de;
    }
    
    @Override
    public void a(final OnListEntriesResponse onListEntriesResponse) {
        this.De.b(new o$h(Status.Jo, new MetadataBuffer(onListEntriesResponse.ii(), null), onListEntriesResponse.ij()));
    }
    
    @Override
    public void o(final Status status) {
        this.De.b(new o$h(status, null, false));
    }
}
