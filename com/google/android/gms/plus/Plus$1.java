// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.plus.internal.h;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api$b;

final class Plus$1 implements Api$b<e, Plus$PlusOptions>
{
    @Override
    public e a(final Context context, final Looper looper, final ClientSettings clientSettings, final Plus$PlusOptions plus$PlusOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        Plus$PlusOptions plus$PlusOptions2 = plus$PlusOptions;
        if (plus$PlusOptions == null) {
            plus$PlusOptions2 = new Plus$PlusOptions((Plus$1)null);
        }
        return new e(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, new h(clientSettings.getAccountNameOrDefault(), clientSettings.getScopesArray(), plus$PlusOptions2.akR.toArray(new String[0]), new String[0], context.getPackageName(), context.getPackageName(), null, new PlusCommonExtras()));
    }
    
    @Override
    public int getPriority() {
        return 2;
    }
}
