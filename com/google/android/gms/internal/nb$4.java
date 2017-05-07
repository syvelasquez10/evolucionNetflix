// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.panorama.PanoramaApi$PanoramaResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.RemoteException;
import com.google.android.gms.panorama.PanoramaApi;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.net.Uri;

final class nb$4 extends mz$a
{
    final /* synthetic */ Uri akn;
    final /* synthetic */ mz akq;
    final /* synthetic */ Context mV;
    
    nb$4(final Context mv, final Uri akn, final mz akq) {
        this.mV = mv;
        this.akn = akn;
        this.akq = akq;
    }
    
    public void a(final int n, final Bundle bundle, final int n2, final Intent intent) {
        a(this.mV, this.akn);
        this.akq.a(n, bundle, n2, intent);
    }
}
