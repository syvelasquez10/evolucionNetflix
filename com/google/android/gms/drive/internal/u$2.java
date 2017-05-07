// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveFolder$DriveFolderResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.drive.MetadataChangeSet;

class u$2 extends u$f
{
    final /* synthetic */ MetadataChangeSet ON;
    final /* synthetic */ u OP;
    
    u$2(final u op, final MetadataChangeSet on) {
        this.OP = op;
        this.ON = on;
    }
    
    @Override
    protected void a(final q q) {
        this.ON.hS().setContext(q.getContext());
        q.hY().a(new CreateFolderRequest(this.OP.getDriveId(), this.ON.hS()), new u$b((BaseImplementation$b<DriveFolder$DriveFolderResult>)this));
    }
}
