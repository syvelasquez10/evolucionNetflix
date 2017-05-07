// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.lq;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.ly;
import com.google.android.gms.common.api.Api;

public class ActivityRecognition
{
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static ActivityRecognitionApi ActivityRecognitionApi;
    public static final String CLIENT_NAME = "activity_recognition";
    private static final Api.c<ly> CU;
    private static final Api.b<ly, Api.ApiOptions.NoOptions> CV;
    
    static {
        CU = new Api.c();
        CV = new Api.b<ly, Api.ApiOptions.NoOptions>() {
            public ly d(final Context context, final Looper looper, final ClientSettings clientSettings, final NoOptions noOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new ly(context, looper, context.getPackageName(), connectionCallbacks, onConnectionFailedListener, "activity_recognition");
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        API = new Api<Api.ApiOptions.NoOptions>((Api.b<C, Api.ApiOptions.NoOptions>)ActivityRecognition.CV, (Api.c<C>)ActivityRecognition.CU, new Scope[0]);
        ActivityRecognition.ActivityRecognitionApi = new lq();
    }
    
    public abstract static class a<R extends Result> extends BaseImplementation.a<R, ly>
    {
        public a() {
            super(ActivityRecognition.CU);
        }
    }
}
