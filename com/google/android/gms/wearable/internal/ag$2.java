// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wearable.MessageApi$MessageListener;
import android.content.IntentFilter;
import com.google.android.gms.common.api.Status;

class ag$2 extends d<Status>
{
    final /* synthetic */ IntentFilter[] avg;
    final /* synthetic */ ag avu;
    final /* synthetic */ MessageApi$MessageListener avv;
    
    ag$2(final ag avu, final MessageApi$MessageListener avv, final IntentFilter[] avg) {
        this.avu = avu;
        this.avv = avv;
        this.avg = avg;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.a((BaseImplementation$b<Status>)this, this.avv, this.avg);
    }
    
    public Status d(final Status status) {
        return new Status(13);
    }
}
