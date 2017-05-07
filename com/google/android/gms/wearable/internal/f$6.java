// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi$GetFdForAssetResult;

class f$6 extends d<DataApi$GetFdForAssetResult>
{
    final /* synthetic */ f avc;
    final /* synthetic */ Asset avd;
    
    f$6(final f avc, final Asset avd) {
        this.avc = avc;
        this.avd = avd;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.a((BaseImplementation$b<DataApi$GetFdForAssetResult>)this, this.avd);
    }
    
    protected DataApi$GetFdForAssetResult aI(final Status status) {
        return new f$c(status, null);
    }
}
