// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.np;
import com.google.android.gms.internal.nq;
import com.google.android.gms.internal.no;
import com.google.android.gms.internal.ns;
import com.google.android.gms.internal.nr;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api$b;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api$c;
import com.google.android.gms.common.api.Api;

public final class Plus
{
    public static final Api<Plus$PlusOptions> API;
    public static final Account AccountApi;
    public static final Api$c<e> CU;
    static final Api$b<e, Plus$PlusOptions> CV;
    public static final Moments MomentsApi;
    public static final People PeopleApi;
    public static final Scope SCOPE_PLUS_LOGIN;
    public static final Scope SCOPE_PLUS_PROFILE;
    public static final b akO;
    public static final a akP;
    
    static {
        CU = new Api$c<e>();
        CV = new Plus$1();
        API = new Api<Plus$PlusOptions>((Api$b<C, Plus$PlusOptions>)Plus.CV, (Api$c<C>)Plus.CU, new Scope[0]);
        SCOPE_PLUS_LOGIN = new Scope("https://www.googleapis.com/auth/plus.login");
        SCOPE_PLUS_PROFILE = new Scope("https://www.googleapis.com/auth/plus.me");
        MomentsApi = new nr();
        PeopleApi = new ns();
        AccountApi = new no();
        akO = new nq();
        akP = new np();
    }
    
    public static e a(final GoogleApiClient googleApiClient, final Api$c<e> api$c) {
        final boolean b = true;
        n.b(googleApiClient != null, (Object)"GoogleApiClient parameter is required.");
        n.a(googleApiClient.isConnected(), (Object)"GoogleApiClient must be connected.");
        final e e = googleApiClient.a(api$c);
        n.a(e != null && b, (Object)"GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        return e;
    }
}
