// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi$DriveContentsResult;

abstract class o$d extends p<DriveApi$DriveContentsResult>
{
    public DriveApi$DriveContentsResult q(final Status status) {
        return new o$c(status, null);
    }
}
