// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation$CallbackHandler;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;

class o$m extends p$a
{
    o$m(final GoogleApiClient googleApiClient, final Status status) {
        this.a((BaseImplementation$CallbackHandler<R>)new BaseImplementation$CallbackHandler<Result>(googleApiClient.a(Drive.CU).getLooper()));
        this.b((R)status);
    }
    
    @Override
    protected void a(final q q) {
    }
}
