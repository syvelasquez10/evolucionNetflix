// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveResource$MetadataResult;
import com.google.android.gms.common.api.BaseImplementation$b;

class w$b extends c
{
    private final BaseImplementation$b<DriveResource$MetadataResult> De;
    
    public w$b(final BaseImplementation$b<DriveResource$MetadataResult> de) {
        this.De = de;
    }
    
    @Override
    public void a(final OnMetadataResponse onMetadataResponse) {
        this.De.b(new w$c(Status.Jo, new l(onMetadataResponse.il())));
    }
    
    @Override
    public void o(final Status status) {
        this.De.b(new w$c(status, null));
    }
}
