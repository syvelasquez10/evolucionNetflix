// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi$DriveIdResult;

abstract class o$g extends p<DriveApi$DriveIdResult>
{
    public DriveApi$DriveIdResult r(final Status status) {
        return new o$f(status, null);
    }
}
