// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.drive.DriveId;

class q$1 extends p$a
{
    final /* synthetic */ y OA;
    final /* synthetic */ q OB;
    final /* synthetic */ DriveId Oy;
    final /* synthetic */ int Oz;
    
    q$1(final q ob, final DriveId oy, final int oz, final y oa) {
        this.OB = ob;
        this.Oy = oy;
        this.Oz = oz;
        this.OA = oa;
    }
    
    @Override
    protected void a(final q q) {
        q.hY().a(new AddEventListenerRequest(this.Oy, this.Oz), this.OA, null, new bb((BaseImplementation$b<Status>)this));
    }
}
