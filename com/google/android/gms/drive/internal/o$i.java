// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi$MetadataBufferResult;

abstract class o$i extends p<DriveApi$MetadataBufferResult>
{
    public DriveApi$MetadataBufferResult s(final Status status) {
        return new o$h(status, null, false);
    }
}
