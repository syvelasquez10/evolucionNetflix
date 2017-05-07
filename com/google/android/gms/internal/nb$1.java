// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.panorama.PanoramaApi$PanoramaResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.RemoteException;
import com.google.android.gms.panorama.PanoramaApi;
import com.google.android.gms.common.api.Result;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import android.content.Context;
import android.os.Bundle;
import android.net.Uri;
import com.google.android.gms.panorama.PanoramaApi$a;

class nb$1 extends nb$d<PanoramaApi$a>
{
    final /* synthetic */ Uri akn;
    final /* synthetic */ Bundle ako;
    
    @Override
    protected void a(final Context context, final na na) {
        a(context, na, new nb$a((BaseImplementation$b<PanoramaApi$a>)this), this.akn, this.ako);
    }
    
    protected PanoramaApi$a ay(final Status status) {
        return new my(status, null, 0);
    }
}
