// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.internal.ly;
import com.google.android.gms.common.api.Api$b;

final class LocationServices$1 implements Api$b<ly, Api$ApiOptions$NoOptions>
{
    public ly d(final Context context, final Looper looper, final ClientSettings clientSettings, final Api$ApiOptions$NoOptions api$ApiOptions$NoOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new ly(context, looper, context.getPackageName(), googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, "locationServices", clientSettings.getAccountName());
    }
    
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
}
