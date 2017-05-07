// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wearable.DataItemBuffer;

class f$3 extends d<DataItemBuffer>
{
    final /* synthetic */ f avc;
    
    f$3(final f avc) {
        this.avc = avc;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.o((BaseImplementation$b<DataItemBuffer>)this);
    }
    
    protected DataItemBuffer aG(final Status status) {
        return new DataItemBuffer(DataHolder.as(status.getStatusCode()));
    }
}
