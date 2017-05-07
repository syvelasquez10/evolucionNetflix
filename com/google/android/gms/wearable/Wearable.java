// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.wearable.internal.e;
import com.google.android.gms.wearable.internal.aj;
import com.google.android.gms.wearable.internal.ag;
import com.google.android.gms.wearable.internal.f;
import com.google.android.gms.wearable.internal.aw;
import com.google.android.gms.common.api.Api;

public class Wearable
{
    public static final Api<WearableOptions> API;
    public static final Api.c<aw> CU;
    private static final Api.b<aw, WearableOptions> CV;
    public static final DataApi DataApi;
    public static final MessageApi MessageApi;
    public static final NodeApi NodeApi;
    public static final b auQ;
    
    static {
        DataApi = new f();
        MessageApi = new ag();
        NodeApi = new aj();
        auQ = new e();
        CU = new Api.c();
        CV = new Api.b<aw, WearableOptions>() {
            public aw a(final Context context, final Looper looper, final ClientSettings clientSettings, final WearableOptions wearableOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                if (wearableOptions == null) {
                    new WearableOptions(new Builder());
                }
                return new aw(context, looper, connectionCallbacks, onConnectionFailedListener);
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        API = new Api<WearableOptions>((Api.b<C, WearableOptions>)Wearable.CV, (Api.c<C>)Wearable.CU, new Scope[0]);
    }
    
    public static final class WearableOptions implements Optional
    {
        private WearableOptions(final Builder builder) {
        }
        
        public static class Builder
        {
            public WearableOptions build() {
                return new WearableOptions(this);
            }
        }
    }
}
