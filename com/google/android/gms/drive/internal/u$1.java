// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveFolder$DriveFileResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.ExecutionOptions;

class u$1 extends u$d
{
    final /* synthetic */ ExecutionOptions OF;
    final /* synthetic */ MetadataChangeSet ON;
    final /* synthetic */ int OO;
    final /* synthetic */ u OP;
    final /* synthetic */ Contents On;
    
    u$1(final u op, final MetadataChangeSet on, final Contents on2, final int oo, final ExecutionOptions of) {
        this.OP = op;
        this.ON = on;
        this.On = on2;
        this.OO = oo;
        this.OF = of;
    }
    
    @Override
    protected void a(final q q) {
        this.ON.hS().setContext(q.getContext());
        int requestId;
        if (this.On == null) {
            requestId = 0;
        }
        else {
            requestId = this.On.getRequestId();
        }
        q.hY().a(new CreateFileRequest(this.OP.getDriveId(), this.ON.hS(), requestId, this.OO, this.OF), new u$a((BaseImplementation$b<DriveFolder$DriveFileResult>)this));
    }
}
