// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$a;
import android.app.PendingIntent;

class lq$2 extends lq$a
{
    final /* synthetic */ PendingIntent aer;
    final /* synthetic */ lq aes;
    
    lq$2(final lq aes, final PendingIntent aer) {
        this.aes = aes;
        this.aer = aer;
        super((lq$1)null);
    }
    
    @Override
    protected void a(final ly ly) {
        ly.removeActivityUpdates(this.aer);
        this.b((R)Status.Jo);
    }
}
