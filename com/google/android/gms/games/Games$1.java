// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$b;

final class Games$1 implements Api$b<GamesClientImpl, Games$GamesOptions>
{
    @Override
    public GamesClientImpl a(final Context context, final Looper looper, final ClientSettings clientSettings, Games$GamesOptions games$GamesOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        if (games$GamesOptions == null) {
            games$GamesOptions = new Games$GamesOptions((Games$1)null);
        }
        return new GamesClientImpl(context, looper, clientSettings.getRealClientPackageName(), clientSettings.getAccountNameOrDefault(), googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, clientSettings.getScopesArray(), clientSettings.getGravityForPopups(), clientSettings.getViewForPopups(), games$GamesOptions);
    }
    
    @Override
    public int getPriority() {
        return 1;
    }
}
