// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.n;
import android.app.Activity;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.ox;
import com.google.android.gms.common.api.Api$b;

final class Wallet$1 implements Api$b<ox, Wallet$WalletOptions>
{
    @Override
    public ox a(final Context context, final Looper looper, final ClientSettings clientSettings, Wallet$WalletOptions wallet$WalletOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        n.b(context instanceof Activity, (Object)"An Activity must be used for Wallet APIs");
        final Activity activity = (Activity)context;
        if (wallet$WalletOptions == null) {
            wallet$WalletOptions = new Wallet$WalletOptions((Wallet$1)null);
        }
        return new ox(activity, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, wallet$WalletOptions.environment, clientSettings.getAccountName(), wallet$WalletOptions.theme);
    }
    
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
}
