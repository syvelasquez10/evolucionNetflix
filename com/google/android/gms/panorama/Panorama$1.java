// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.panorama;

import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.internal.nc;
import com.google.android.gms.common.api.Api$b;

final class Panorama$1 implements Api$b<nc, Api$ApiOptions$NoOptions>
{
    public nc e(final Context context, final Looper looper, final ClientSettings clientSettings, final Api$ApiOptions$NoOptions api$ApiOptions$NoOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new nc(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
}
