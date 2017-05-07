// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveResource$MetadataResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.drive.MetadataChangeSet;

class w$4 extends w$d
{
    final /* synthetic */ MetadataChangeSet ON;
    final /* synthetic */ w OT;
    
    w$4(final w ot, final MetadataChangeSet on) {
        this.OT = ot;
        this.ON = on;
        super(ot, null);
    }
    
    @Override
    protected void a(final q q) {
        this.ON.hS().setContext(q.getContext());
        q.hY().a(new UpdateMetadataRequest(this.OT.MO, this.ON.hS()), new w$b((BaseImplementation$b<DriveResource$MetadataResult>)this));
    }
}
