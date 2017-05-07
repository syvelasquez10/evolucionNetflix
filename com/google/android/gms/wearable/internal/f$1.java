// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.DataApi$DataItemResult;

class f$1 extends d<DataApi$DataItemResult>
{
    final /* synthetic */ PutDataRequest avb;
    final /* synthetic */ f avc;
    
    f$1(final f avc, final PutDataRequest avb) {
        this.avc = avc;
        this.avb = avb;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.a((BaseImplementation$b<DataApi$DataItemResult>)this, this.avb);
    }
    
    public DataApi$DataItemResult aF(final Status status) {
        return new f$a(status, null);
    }
}
