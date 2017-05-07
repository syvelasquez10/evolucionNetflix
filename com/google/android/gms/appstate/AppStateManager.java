// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.ib;
import com.google.android.gms.common.api.Api;

public final class AppStateManager
{
    public static final Api<Api.ApiOptions.NoOptions> API;
    static final Api.c<ib> CU;
    private static final Api.b<ib, Api.ApiOptions.NoOptions> CV;
    public static final Scope SCOPE_APP_STATE;
    
    static {
        CU = new Api.c();
        CV = new Api.b<ib, Api.ApiOptions.NoOptions>() {
            public ib b(final Context context, final Looper looper, final ClientSettings clientSettings, final NoOptions noOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new ib(context, looper, connectionCallbacks, onConnectionFailedListener, clientSettings.getAccountNameOrDefault(), clientSettings.getScopes().toArray(new String[0]));
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        SCOPE_APP_STATE = new Scope("https://www.googleapis.com/auth/appstate");
        API = new Api<Api.ApiOptions.NoOptions>((Api.b<C, Api.ApiOptions.NoOptions>)AppStateManager.CV, (Api.c<C>)AppStateManager.CU, new Scope[] { AppStateManager.SCOPE_APP_STATE });
    }
    
    public static ib a(final GoogleApiClient googleApiClient) {
        final boolean b = true;
        n.b(googleApiClient != null, (Object)"GoogleApiClient parameter is required.");
        n.a(googleApiClient.isConnected(), (Object)"GoogleApiClient must be connected.");
        final ib ib = googleApiClient.a(AppStateManager.CU);
        n.a(ib != null && b, (Object)"GoogleApiClient is not configured to use the AppState API. Pass AppStateManager.API into GoogleApiClient.Builder#addApi() to use this feature.");
        return ib;
    }
    
    public static PendingResult<StateDeletedResult> delete(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.b((PendingResult<StateDeletedResult>)new b() {
            protected void a(final ib ib) {
                ib.a((BaseImplementation.b<StateDeletedResult>)this, n);
            }
            
            public StateDeletedResult g(final Status status) {
                return new StateDeletedResult() {
                    @Override
                    public int getStateKey() {
                        return n;
                    }
                    
                    @Override
                    public Status getStatus() {
                        return status;
                    }
                };
            }
        });
    }
    
    private static StateResult e(final Status status) {
        return (StateResult)new StateResult() {
            @Override
            public StateConflictResult getConflictResult() {
                return null;
            }
            
            @Override
            public StateLoadedResult getLoadedResult() {
                return null;
            }
            
            @Override
            public Status getStatus() {
                return status;
            }
            
            @Override
            public void release() {
            }
        };
    }
    
    public static int getMaxNumKeys(final GoogleApiClient googleApiClient) {
        return a(googleApiClient).fs();
    }
    
    public static int getMaxStateSize(final GoogleApiClient googleApiClient) {
        return a(googleApiClient).fr();
    }
    
    public static PendingResult<StateListResult> list(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<StateListResult>)new c() {
            protected void a(final ib ib) {
                ib.a((BaseImplementation.b<StateListResult>)this);
            }
        });
    }
    
    public static PendingResult<StateResult> load(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.a((PendingResult<StateResult>)new e() {
            protected void a(final ib ib) {
                ib.b((BaseImplementation.b<StateResult>)this, n);
            }
        });
    }
    
    public static PendingResult<StateResult> resolve(final GoogleApiClient googleApiClient, final int n, final String s, final byte[] array) {
        return googleApiClient.b((PendingResult<StateResult>)new e() {
            protected void a(final ib ib) {
                ib.a((BaseImplementation.b<StateResult>)this, n, s, array);
            }
        });
    }
    
    public static PendingResult<Status> signOut(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new d() {
            protected void a(final ib ib) {
                ib.b((BaseImplementation.b<Status>)this);
            }
        });
    }
    
    public static void update(final GoogleApiClient googleApiClient, final int n, final byte[] array) {
        googleApiClient.b(new e() {
            protected void a(final ib ib) {
                ib.a(null, n, array);
            }
        });
    }
    
    public static PendingResult<StateResult> updateImmediate(final GoogleApiClient googleApiClient, final int n, final byte[] array) {
        return googleApiClient.b((PendingResult<StateResult>)new e() {
            protected void a(final ib ib) {
                ib.a((BaseImplementation.b<StateResult>)this, n, array);
            }
        });
    }
    
    public interface StateConflictResult extends Releasable, Result
    {
        byte[] getLocalData();
        
        String getResolvedVersion();
        
        byte[] getServerData();
        
        int getStateKey();
    }
    
    public interface StateDeletedResult extends Result
    {
        int getStateKey();
    }
    
    public interface StateListResult extends Result
    {
        AppStateBuffer getStateBuffer();
    }
    
    public interface StateLoadedResult extends Releasable, Result
    {
        byte[] getLocalData();
        
        int getStateKey();
    }
    
    public interface StateResult extends Releasable, Result
    {
        StateConflictResult getConflictResult();
        
        StateLoadedResult getLoadedResult();
    }
    
    public abstract static class a<R extends Result> extends BaseImplementation.a<R, ib>
    {
        public a() {
            super(AppStateManager.CU);
        }
    }
    
    private abstract static class b extends a<StateDeletedResult>
    {
    }
    
    private abstract static class c extends a<StateListResult>
    {
        public StateListResult h(final Status status) {
            return new StateListResult() {
                @Override
                public AppStateBuffer getStateBuffer() {
                    return new AppStateBuffer(null);
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class d extends a<Status>
    {
        public Status d(final Status status) {
            return status;
        }
    }
    
    private abstract static class e extends a<StateResult>
    {
        public StateResult i(final Status status) {
            return e(status);
        }
    }
}
