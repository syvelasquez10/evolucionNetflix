// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.common.api.Result;
import java.util.HashSet;
import java.util.Set;
import com.google.android.gms.internal.fq;
import com.google.android.gms.internal.hz;
import com.google.android.gms.internal.hy;
import com.google.android.gms.internal.ib;
import com.google.android.gms.internal.ia;
import com.google.android.gms.plus.internal.h;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.fc;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api;

public final class Plus
{
    public static final Api<PlusOptions> API;
    public static final Account AccountApi;
    public static final Moments MomentsApi;
    public static final People PeopleApi;
    public static final Scope SCOPE_PLUS_LOGIN;
    public static final Scope SCOPE_PLUS_PROFILE;
    public static final com.google.android.gms.plus.a TI;
    public static final Api.c<e> wx;
    static final Api.b<e, PlusOptions> wy;
    
    static {
        wx = new Api.c();
        wy = new Api.b<e, PlusOptions>() {
            public e a(final Context context, final Looper looper, final fc fc, final PlusOptions plusOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                PlusOptions plusOptions2 = plusOptions;
                if (plusOptions == null) {
                    plusOptions2 = new PlusOptions();
                }
                return new e(context, looper, connectionCallbacks, onConnectionFailedListener, new h(fc.eC(), fc.eF(), plusOptions2.TK.toArray(new String[0]), new String[0], context.getPackageName(), context.getPackageName(), null, new PlusCommonExtras()));
            }
            
            @Override
            public int getPriority() {
                return 2;
            }
        };
        API = new Api<PlusOptions>((Api.b<C, PlusOptions>)Plus.wy, (Api.c<C>)Plus.wx, new Scope[0]);
        SCOPE_PLUS_LOGIN = new Scope("https://www.googleapis.com/auth/plus.login");
        SCOPE_PLUS_PROFILE = new Scope("https://www.googleapis.com/auth/plus.me");
        MomentsApi = new ia();
        PeopleApi = new ib();
        AccountApi = new hy();
        TI = new hz();
    }
    
    public static e a(final GoogleApiClient googleApiClient, final Api.c<e> c) {
        final boolean b = true;
        fq.b(googleApiClient != null, "GoogleApiClient parameter is required.");
        fq.a(googleApiClient.isConnected(), (Object)"GoogleApiClient must be connected.");
        final e e = googleApiClient.a(c);
        fq.a(e != null && b, (Object)"GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        return e;
    }
    
    public static final class PlusOptions implements Optional
    {
        final String TJ;
        final Set<String> TK;
        
        private PlusOptions() {
            this.TJ = null;
            this.TK = new HashSet<String>();
        }
        
        private PlusOptions(final Builder builder) {
            this.TJ = builder.TJ;
            this.TK = builder.TK;
        }
        
        public static Builder builder() {
            return new Builder();
        }
        
        public static final class Builder
        {
            String TJ;
            final Set<String> TK;
            
            public Builder() {
                this.TK = new HashSet<String>();
            }
            
            public Builder addActivityTypes(final String... array) {
                fq.b(array, "activityTypes may not be null.");
                for (int i = 0; i < array.length; ++i) {
                    this.TK.add(array[i]);
                }
                return this;
            }
            
            public PlusOptions build() {
                return new PlusOptions(this);
            }
            
            public Builder setServerClientId(final String tj) {
                this.TJ = tj;
                return this;
            }
        }
    }
    
    public abstract static class a<R extends Result> extends b<R, e>
    {
        public a() {
            super(Plus.wx);
        }
    }
}
