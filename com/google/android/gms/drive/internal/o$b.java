// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi$ContentsResult;

abstract class o$b extends p<DriveApi$ContentsResult>
{
    public DriveApi$ContentsResult p(final Status status) {
        return new o$a(status, null);
    }
}
