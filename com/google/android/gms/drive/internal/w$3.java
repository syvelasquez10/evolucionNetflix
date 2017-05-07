// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.Api$a;
import java.util.List;

class w$3 extends p$a
{
    final /* synthetic */ w OT;
    final /* synthetic */ List OU;
    
    w$3(final w ot, final List ou) {
        this.OT = ot;
        this.OU = ou;
    }
    
    @Override
    protected void a(final q q) {
        q.hY().a(new SetResourceParentsRequest(this.OT.MO, this.OU), new bb((BaseImplementation$b<Status>)this));
    }
}
