// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness;

import com.google.android.gms.internal.ld;
import com.google.android.gms.internal.kw;
import android.os.Build$VERSION;
import com.google.android.gms.internal.kz;
import com.google.android.gms.internal.kx;
import com.google.android.gms.internal.ky;
import com.google.android.gms.internal.lc;
import com.google.android.gms.internal.la;
import com.google.android.gms.internal.lb;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.kk;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.ku;
import com.google.android.gms.internal.kj;
import com.google.android.gms.common.api.Api;

public class Fitness
{
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final BleApi BleApi;
    public static final Api.c<kj> CU;
    private static final Api.b<kj, Api.ApiOptions.NoOptions> CV;
    public static final ConfigApi ConfigApi;
    public static final HistoryApi HistoryApi;
    public static final RecordingApi RecordingApi;
    public static final SensorsApi SensorsApi;
    public static final SessionsApi SessionsApi;
    public static final ku Sf;
    
    static {
        CU = new Api.c();
        CV = new Api.b<kj, Api.ApiOptions.NoOptions>() {
            public kj c(final Context context, final Looper looper, final ClientSettings clientSettings, final NoOptions noOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new kk(context, looper, connectionCallbacks, onConnectionFailedListener, clientSettings.getAccountNameOrDefault(), FitnessScopes.d(clientSettings.getScopes()));
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        API = new Api<Api.ApiOptions.NoOptions>((Api.b<C, Api.ApiOptions.NoOptions>)Fitness.CV, (Api.c<C>)Fitness.CU, new Scope[0]);
        SensorsApi = new lb();
        RecordingApi = new la();
        SessionsApi = new lc();
        HistoryApi = new ky();
        ConfigApi = new kx();
        BleApi = iy();
        Sf = new kz();
    }
    
    private static BleApi iy() {
        if (Build$VERSION.SDK_INT >= 18) {
            return new kw();
        }
        return new ld();
    }
}
