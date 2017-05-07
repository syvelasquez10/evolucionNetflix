// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$a;
import android.app.PendingIntent;

class lq$1 extends lq$a
{
    final /* synthetic */ long aeq;
    final /* synthetic */ PendingIntent aer;
    final /* synthetic */ lq aes;
    
    lq$1(final lq aes, final long aeq, final PendingIntent aer) {
        this.aes = aes;
        this.aeq = aeq;
        this.aer = aer;
        super((lq$1)null);
    }
    
    @Override
    protected void a(final ly ly) {
        ly.requestActivityUpdates(this.aeq, this.aer);
        this.b((R)Status.Jo);
    }
}
