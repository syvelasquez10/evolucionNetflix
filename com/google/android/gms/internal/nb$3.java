// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.RemoteException;
import com.google.android.gms.panorama.PanoramaApi;
import android.os.Bundle;
import com.google.android.gms.panorama.PanoramaApi$PanoramaResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import android.content.Context;
import android.net.Uri;

class nb$3 extends nb$b
{
    final /* synthetic */ Uri akn;
    final /* synthetic */ nb akp;
    
    nb$3(final nb akp, final Uri akn) {
        this.akp = akp;
        this.akn = akn;
        super((nb$1)null);
    }
    
    @Override
    protected void a(final Context context, final na na) {
        a(context, na, new nb$c((BaseImplementation$b<PanoramaApi$PanoramaResult>)this), this.akn, null);
    }
}
