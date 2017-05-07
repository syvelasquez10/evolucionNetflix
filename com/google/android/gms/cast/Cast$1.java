// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api$ApiOptions$HasOptions;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.Api$b;

final class Cast$1 implements Api$b<ij, Cast$CastOptions>
{
    @Override
    public ij a(final Context context, final Looper looper, final ClientSettings clientSettings, final Cast$CastOptions cast$CastOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        n.b(cast$CastOptions, "Setting the API options is required.");
        return new ij(context, looper, cast$CastOptions.EK, cast$CastOptions.EM, cast$CastOptions.EL, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
}
