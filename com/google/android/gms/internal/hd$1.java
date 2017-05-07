// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api$b;

final class hd$1 implements Api$b<hy, Api$ApiOptions$NoOptions>
{
    @Override
    public hy a(final Context context, final Looper looper, final ClientSettings clientSettings, final Api$ApiOptions$NoOptions api$ApiOptions$NoOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new hy(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
}
