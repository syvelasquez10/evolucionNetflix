// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import android.net.Uri;
import com.google.android.gms.wearable.DataItemBuffer;

class f$4 extends d<DataItemBuffer>
{
    final /* synthetic */ Uri akn;
    final /* synthetic */ f avc;
    
    f$4(final f avc, final Uri akn) {
        this.avc = avc;
        this.akn = akn;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.b((BaseImplementation$b<DataItemBuffer>)this, this.akn);
    }
    
    protected DataItemBuffer aG(final Status status) {
        return new DataItemBuffer(DataHolder.as(status.getStatusCode()));
    }
}
