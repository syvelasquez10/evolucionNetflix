// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import android.net.Uri;
import com.google.android.gms.wearable.DataApi$DeleteDataItemsResult;

class f$5 extends d<DataApi$DeleteDataItemsResult>
{
    final /* synthetic */ Uri akn;
    final /* synthetic */ f avc;
    
    f$5(final f avc, final Uri akn) {
        this.avc = avc;
        this.akn = akn;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.c((BaseImplementation$b<DataApi$DeleteDataItemsResult>)this, this.akn);
    }
    
    protected DataApi$DeleteDataItemsResult aH(final Status status) {
        return new f$b(status, 0);
    }
}
