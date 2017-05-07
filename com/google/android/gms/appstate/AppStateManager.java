// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api$b;
import com.google.android.gms.internal.ib;
import com.google.android.gms.common.api.Api$c;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api;

public final class AppStateManager
{
    public static final Api<Api$ApiOptions$NoOptions> API;
    static final Api$c<ib> CU;
    private static final Api$b<ib, Api$ApiOptions$NoOptions> CV;
    public static final Scope SCOPE_APP_STATE;
    
    static {
        CU = new Api$c<ib>();
        CV = new AppStateManager$1();
        SCOPE_APP_STATE = new Scope("https://www.googleapis.com/auth/appstate");
        API = new Api<Api$ApiOptions$NoOptions>((Api$b<C, Api$ApiOptions$NoOptions>)AppStateManager.CV, (Api$c<C>)AppStateManager.CU, new Scope[] { AppStateManager.SCOPE_APP_STATE });
    }
    
    public static ib a(final GoogleApiClient googleApiClient) {
        final boolean b = true;
        n.b(googleApiClient != null, (Object)"GoogleApiClient parameter is required.");
        n.a(googleApiClient.isConnected(), (Object)"GoogleApiClient must be connected.");
        final ib ib = googleApiClient.a(AppStateManager.CU);
        n.a(ib != null && b, (Object)"GoogleApiClient is not configured to use the AppState API. Pass AppStateManager.API into GoogleApiClient.Builder#addApi() to use this feature.");
        return ib;
    }
    
    public static PendingResult<AppStateManager$StateDeletedResult> delete(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.b((PendingResult<AppStateManager$StateDeletedResult>)new AppStateManager$5(n));
    }
    
    private static AppStateManager$StateResult e(final Status status) {
        return new AppStateManager$2(status);
    }
    
    public static int getMaxNumKeys(final GoogleApiClient googleApiClient) {
        return a(googleApiClient).fs();
    }
    
    public static int getMaxStateSize(final GoogleApiClient googleApiClient) {
        return a(googleApiClient).fr();
    }
    
    public static PendingResult<AppStateManager$StateListResult> list(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<AppStateManager$StateListResult>)new AppStateManager$7());
    }
    
    public static PendingResult<AppStateManager$StateResult> load(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.a((PendingResult<AppStateManager$StateResult>)new AppStateManager$6(n));
    }
    
    public static PendingResult<AppStateManager$StateResult> resolve(final GoogleApiClient googleApiClient, final int n, final String s, final byte[] array) {
        return googleApiClient.b((PendingResult<AppStateManager$StateResult>)new AppStateManager$8(n, s, array));
    }
    
    public static PendingResult<Status> signOut(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new AppStateManager$9());
    }
    
    public static void update(final GoogleApiClient googleApiClient, final int n, final byte[] array) {
        googleApiClient.b(new AppStateManager$3(n, array));
    }
    
    public static PendingResult<AppStateManager$StateResult> updateImmediate(final GoogleApiClient googleApiClient, final int n, final byte[] array) {
        return googleApiClient.b((PendingResult<AppStateManager$StateResult>)new AppStateManager$4(n, array));
    }
}
