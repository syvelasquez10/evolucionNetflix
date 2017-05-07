// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveResource$MetadataResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;

class w$1 extends w$d
{
    final /* synthetic */ w OT;
    
    w$1(final w ot) {
        this.OT = ot;
        super(ot, null);
    }
    
    @Override
    protected void a(final q q) {
        q.hY().a(new GetMetadataRequest(this.OT.MO), new w$b((BaseImplementation$b<DriveResource$MetadataResult>)this));
    }
}
