// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.drive.DriveId;

class q$3 extends p$a
{
    final /* synthetic */ q OB;
    final /* synthetic */ DriveId Oy;
    final /* synthetic */ int Oz;
    
    q$3(final q ob, final DriveId oy, final int oz) {
        this.OB = ob;
        this.Oy = oy;
        this.Oz = oz;
    }
    
    @Override
    protected void a(final q q) {
        q.hY().a(new AddEventListenerRequest(this.Oy, this.Oz), null, null, new bb((BaseImplementation$b<Status>)this));
    }
}
