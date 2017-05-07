// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents;

import com.google.android.gms.common.internal.n;
import android.app.Activity;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.ll;
import com.google.android.gms.common.api.Api$b;

final class Address$1 implements Api$b<ll, Address$AddressOptions>
{
    @Override
    public ll a(final Context context, final Looper looper, final ClientSettings clientSettings, final Address$AddressOptions address$AddressOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        n.b(context instanceof Activity, (Object)"An Activity must be used for Address APIs");
        Address$AddressOptions address$AddressOptions2 = address$AddressOptions;
        if (address$AddressOptions == null) {
            address$AddressOptions2 = new Address$AddressOptions();
        }
        return new ll((Activity)context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, clientSettings.getAccountName(), address$AddressOptions2.theme);
    }
    
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
}
