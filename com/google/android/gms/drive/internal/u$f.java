// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder$DriveFolderResult;

abstract class u$f extends p<DriveFolder$DriveFolderResult>
{
    public DriveFolder$DriveFolderResult u(final Status status) {
        return new u$e(status, null);
    }
}
