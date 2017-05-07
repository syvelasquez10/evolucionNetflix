// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wearable.MessageApi$MessageListener;
import com.google.android.gms.common.api.Status;

class ag$3 extends d<Status>
{
    final /* synthetic */ ag avu;
    final /* synthetic */ MessageApi$MessageListener avv;
    
    ag$3(final ag avu, final MessageApi$MessageListener avv) {
        this.avu = avu;
        this.avv = avv;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.a((BaseImplementation$b<Status>)this, this.avv);
    }
    
    public Status d(final Status status) {
        return new Status(13);
    }
}
