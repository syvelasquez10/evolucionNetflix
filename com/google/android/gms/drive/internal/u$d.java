// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder$DriveFileResult;

abstract class u$d extends p<DriveFolder$DriveFileResult>
{
    public DriveFolder$DriveFileResult t(final Status status) {
        return new u$c(status, null);
    }
}
