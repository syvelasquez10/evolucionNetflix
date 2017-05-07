// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.panorama;

import com.google.android.gms.internal.hw;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.fc;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.hx;
import com.google.android.gms.common.api.Api;

public final class Panorama
{
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final PanoramaApi PanoramaApi;
    public static final Api.c<hx> wx;
    static final Api.b<hx, Api.ApiOptions.NoOptions> wy;
    
    static {
        wx = new Api.c();
        wy = new Api.b<hx, Api.ApiOptions.NoOptions>() {
            public hx c(final Context context, final Looper looper, final fc fc, final NoOptions noOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new hx(context, looper, connectionCallbacks, onConnectionFailedListener);
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        API = new Api<Api.ApiOptions.NoOptions>((Api.b<C, Api.ApiOptions.NoOptions>)Panorama.wy, (Api.c<C>)Panorama.wx, new Scope[0]);
        PanoramaApi = new hw();
    }
}
