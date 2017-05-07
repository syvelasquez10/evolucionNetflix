// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import android.content.IntentFilter;
import com.google.android.gms.wearable.DataApi$DataListener;
import com.google.android.gms.common.api.Status;

class f$8 extends d<Status>
{
    final /* synthetic */ f avc;
    final /* synthetic */ DataApi$DataListener avf;
    final /* synthetic */ IntentFilter[] avg;
    
    f$8(final f avc, final DataApi$DataListener avf, final IntentFilter[] avg) {
        this.avc = avc;
        this.avf = avf;
        this.avg = avg;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.a((BaseImplementation$b<Status>)this, this.avf, this.avg);
    }
    
    public Status d(final Status status) {
        return new Status(13);
    }
}
