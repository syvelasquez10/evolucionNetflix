// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.DataApi$GetFdForAssetResult;

class f$7 extends d<DataApi$GetFdForAssetResult>
{
    final /* synthetic */ f avc;
    final /* synthetic */ DataItemAsset ave;
    
    f$7(final f avc, final DataItemAsset ave) {
        this.avc = avc;
        this.ave = ave;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.a((BaseImplementation$b<DataApi$GetFdForAssetResult>)this, this.ave);
    }
    
    protected DataApi$GetFdForAssetResult aI(final Status status) {
        return new f$c(status, null);
    }
}
