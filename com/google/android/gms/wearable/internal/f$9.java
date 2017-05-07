// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wearable.DataApi$DataListener;
import com.google.android.gms.common.api.Status;

class f$9 extends d<Status>
{
    final /* synthetic */ f avc;
    final /* synthetic */ DataApi$DataListener avf;
    
    f$9(final f avc, final DataApi$DataListener avf) {
        this.avc = avc;
        this.avf = avf;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.a((BaseImplementation$b<Status>)this, this.avf);
    }
    
    public Status d(final Status status) {
        return new Status(13);
    }
}
