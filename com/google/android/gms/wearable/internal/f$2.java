// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import android.net.Uri;
import com.google.android.gms.wearable.DataApi$DataItemResult;

class f$2 extends d<DataApi$DataItemResult>
{
    final /* synthetic */ Uri akn;
    final /* synthetic */ f avc;
    
    f$2(final f avc, final Uri akn) {
        this.avc = avc;
        this.akn = akn;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.a((BaseImplementation$b<DataApi$DataItemResult>)this, this.akn);
    }
    
    protected DataApi$DataItemResult aF(final Status status) {
        return new f$a(status, null);
    }
}
