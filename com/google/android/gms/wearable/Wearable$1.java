// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.wearable.internal.aw;
import com.google.android.gms.common.api.Api$b;

final class Wearable$1 implements Api$b<aw, Wearable$WearableOptions>
{
    @Override
    public aw a(final Context context, final Looper looper, final ClientSettings clientSettings, final Wearable$WearableOptions wearable$WearableOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        if (wearable$WearableOptions == null) {
            new Wearable$WearableOptions(new Wearable$WearableOptions$Builder(), null);
        }
        return new aw(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
}
