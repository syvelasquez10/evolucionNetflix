// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi$ContentsResult;

class o$a implements DriveApi$ContentsResult
{
    private final Status CM;
    private final Contents Op;
    
    public o$a(final Status cm, final Contents op) {
        this.CM = cm;
        this.Op = op;
    }
    
    @Override
    public Contents getContents() {
        return this.Op;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
