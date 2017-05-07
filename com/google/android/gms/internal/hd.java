// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Api;

public final class hd
{
    public static final Api.c<hy> BN;
    private static final Api.b<hy, Api.ApiOptions.NoOptions> BO;
    public static final Api<Api.ApiOptions.NoOptions> BP;
    public static final hu BQ;
    
    static {
        BN = new Api.c();
        BO = new Api.b<hy, Api.ApiOptions.NoOptions>() {
            public hy a(final Context context, final Looper looper, final ClientSettings clientSettings, final NoOptions noOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new hy(context, looper, connectionCallbacks, onConnectionFailedListener);
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        BP = new Api<Api.ApiOptions.NoOptions>((Api.b<C, Api.ApiOptions.NoOptions>)hd.BO, (Api.c<C>)hd.BN, new Scope[0]);
        BQ = new hz();
    }
}
