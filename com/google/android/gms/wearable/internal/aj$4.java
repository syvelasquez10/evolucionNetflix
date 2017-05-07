// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wearable.NodeApi$NodeListener;
import com.google.android.gms.common.api.Status;

class aj$4 extends d<Status>
{
    final /* synthetic */ aj avy;
    final /* synthetic */ NodeApi$NodeListener avz;
    
    aj$4(final aj avy, final NodeApi$NodeListener avz) {
        this.avy = avy;
        this.avz = avz;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.b((BaseImplementation$b<Status>)this, this.avz);
    }
    
    public Status d(final Status status) {
        return new Status(13);
    }
}
