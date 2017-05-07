// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.panorama;

import com.google.android.gms.internal.nb;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.nc;
import com.google.android.gms.common.api.Api;

public final class Panorama
{
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Api.c<nc> CU;
    static final Api.b<nc, Api.ApiOptions.NoOptions> CV;
    public static final PanoramaApi PanoramaApi;
    
    static {
        CU = new Api.c();
        CV = new Api.b<nc, Api.ApiOptions.NoOptions>() {
            public nc e(final Context context, final Looper looper, final ClientSettings clientSettings, final NoOptions noOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new nc(context, looper, connectionCallbacks, onConnectionFailedListener);
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        API = new Api<Api.ApiOptions.NoOptions>((Api.b<C, Api.ApiOptions.NoOptions>)Panorama.CV, (Api.c<C>)Panorama.CU, new Scope[0]);
        PanoramaApi = new nb();
    }
}
