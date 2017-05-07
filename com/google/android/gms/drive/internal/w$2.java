// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveApi$MetadataBufferResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;

class w$2 extends o$i
{
    final /* synthetic */ w OT;
    
    w$2(final w ot) {
        this.OT = ot;
    }
    
    @Override
    protected void a(final q q) {
        q.hY().a(new ListParentsRequest(this.OT.MO), new w$a((BaseImplementation$b<DriveApi$MetadataBufferResult>)this));
    }
}
