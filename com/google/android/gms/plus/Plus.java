// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Result;
import java.util.HashSet;
import java.util.Set;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.internal.np;
import com.google.android.gms.internal.nq;
import com.google.android.gms.internal.no;
import com.google.android.gms.internal.ns;
import com.google.android.gms.internal.nr;
import com.google.android.gms.plus.internal.h;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api;

public final class Plus
{
    public static final Api<PlusOptions> API;
    public static final Account AccountApi;
    public static final Api.c<e> CU;
    static final Api.b<e, PlusOptions> CV;
    public static final Moments MomentsApi;
    public static final People PeopleApi;
    public static final Scope SCOPE_PLUS_LOGIN;
    public static final Scope SCOPE_PLUS_PROFILE;
    public static final b akO;
    public static final com.google.android.gms.plus.a akP;
    
    static {
        CU = new Api.c();
        CV = new Api.b<e, PlusOptions>() {
            public e a(final Context context, final Looper looper, final ClientSettings clientSettings, final PlusOptions plusOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                PlusOptions plusOptions2 = plusOptions;
                if (plusOptions == null) {
                    plusOptions2 = new PlusOptions();
                }
                return new e(context, looper, connectionCallbacks, onConnectionFailedListener, new h(clientSettings.getAccountNameOrDefault(), clientSettings.getScopesArray(), plusOptions2.akR.toArray(new String[0]), new String[0], context.getPackageName(), context.getPackageName(), null, new PlusCommonExtras()));
            }
            
            @Override
            public int getPriority() {
                return 2;
            }
        };
        API = new Api<PlusOptions>((Api.b<C, PlusOptions>)Plus.CV, (Api.c<C>)Plus.CU, new Scope[0]);
        SCOPE_PLUS_LOGIN = new Scope("https://www.googleapis.com/auth/plus.login");
        SCOPE_PLUS_PROFILE = new Scope("https://www.googleapis.com/auth/plus.me");
        MomentsApi = new nr();
        PeopleApi = new ns();
        AccountApi = new no();
        akO = new nq();
        akP = new np();
    }
    
    public static e a(final GoogleApiClient googleApiClient, final Api.c<e> c) {
        final boolean b = true;
        n.b(googleApiClient != null, (Object)"GoogleApiClient parameter is required.");
        n.a(googleApiClient.isConnected(), (Object)"GoogleApiClient must be connected.");
        final e e = googleApiClient.a(c);
        n.a(e != null && b, (Object)"GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        return e;
    }
    
    public static final class PlusOptions implements Optional
    {
        final String akQ;
        final Set<String> akR;
        
        private PlusOptions() {
            this.akQ = null;
            this.akR = new HashSet<String>();
        }
        
        private PlusOptions(final Builder builder) {
            this.akQ = builder.akQ;
            this.akR = builder.akR;
        }
        
        public static Builder builder() {
            return new Builder();
        }
        
        public static final class Builder
        {
            String akQ;
            final Set<String> akR;
            
            public Builder() {
                this.akR = new HashSet<String>();
            }
            
            public Builder addActivityTypes(final String... array) {
                n.b(array, "activityTypes may not be null.");
                for (int i = 0; i < array.length; ++i) {
                    this.akR.add(array[i]);
                }
                return this;
            }
            
            public PlusOptions build() {
                return new PlusOptions(this);
            }
            
            public Builder setServerClientId(final String akQ) {
                this.akQ = akQ;
                return this;
            }
        }
    }
    
    public abstract static class a<R extends Result> extends BaseImplementation.a<R, e>
    {
        public a() {
            super(Plus.CU);
        }
    }
}
