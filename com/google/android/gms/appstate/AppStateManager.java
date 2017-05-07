// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.internal.fq;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.fc;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.ei;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api;

public final class AppStateManager
{
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Scope SCOPE_APP_STATE;
    static final Api.c<ei> wx;
    private static final Api.b<ei, Api.ApiOptions.NoOptions> wy;
    
    static {
        wx = new Api.c();
        wy = new Api.b<ei, Api.ApiOptions.NoOptions>() {
            public ei a(final Context context, final Looper looper, final fc fc, final NoOptions noOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new ei(context, looper, connectionCallbacks, onConnectionFailedListener, fc.eC(), fc.eE().toArray(new String[0]));
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        SCOPE_APP_STATE = new Scope("https://www.googleapis.com/auth/appstate");
        API = new Api<Api.ApiOptions.NoOptions>((Api.b<C, Api.ApiOptions.NoOptions>)AppStateManager.wy, (Api.c<C>)AppStateManager.wx, new Scope[] { AppStateManager.SCOPE_APP_STATE });
    }
    
    private static StateResult a(final Status status) {
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
    
    public static ei a(final GoogleApiClient googleApiClient) {
        final boolean b = true;
        fq.b(googleApiClient != null, "GoogleApiClient parameter is required.");
        fq.a(googleApiClient.isConnected(), (Object)"GoogleApiClient must be connected.");
        final ei ei = googleApiClient.a(AppStateManager.wx);
        fq.a(ei != null && b, (Object)"GoogleApiClient is not configured to use the AppState API. Pass AppStateManager.API into GoogleApiClient.Builder#addApi() to use this feature.");
        return ei;
    }
    
    public static PendingResult<StateDeletedResult> delete(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.b((PendingResult<StateDeletedResult>)new b() {
            protected void a(final ei ei) {
                ei.a((com.google.android.gms.common.api.a.d<StateDeletedResult>)this, n);
            }
            
            public StateDeletedResult c(final Status status) {
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
    
    public static int getMaxNumKeys(final GoogleApiClient googleApiClient) {
        return a(googleApiClient).dw();
    }
    
    public static int getMaxStateSize(final GoogleApiClient googleApiClient) {
        return a(googleApiClient).dv();
    }
    
    public static PendingResult<StateListResult> list(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<StateListResult>)new c() {
            protected void a(final ei ei) {
                ei.a((com.google.android.gms.common.api.a.d<StateListResult>)this);
            }
        });
    }
    
    public static PendingResult<StateResult> load(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.a((PendingResult<StateResult>)new e() {
            protected void a(final ei ei) {
                ei.b((com.google.android.gms.common.api.a.d<StateResult>)this, n);
            }
        });
    }
    
    public static PendingResult<StateResult> resolve(final GoogleApiClient googleApiClient, final int n, final String s, final byte[] array) {
        return googleApiClient.b((PendingResult<StateResult>)new e() {
            protected void a(final ei ei) {
                ei.a((com.google.android.gms.common.api.a.d<StateResult>)this, n, s, array);
            }
        });
    }
    
    public static PendingResult<Status> signOut(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new d() {
            protected void a(final ei ei) {
                ei.b((com.google.android.gms.common.api.a.d<Status>)this);
            }
        });
    }
    
    public static void update(final GoogleApiClient googleApiClient, final int n, final byte[] array) {
        googleApiClient.b(new e() {
            protected void a(final ei ei) {
                ei.a(null, n, array);
            }
        });
    }
    
    public static PendingResult<StateResult> updateImmediate(final GoogleApiClient googleApiClient, final int n, final byte[] array) {
        return googleApiClient.b((PendingResult<StateResult>)new e() {
            protected void a(final ei ei) {
                ei.a((com.google.android.gms.common.api.a.d<StateResult>)this, n, array);
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
    
    public abstract static class a<R extends Result> extends com.google.android.gms.common.api.a.b<R, ei>
    {
        public a() {
            super(AppStateManager.wx);
        }
    }
    
    private abstract static class b extends a<StateDeletedResult>
    {
    }
    
    private abstract static class c extends a<StateListResult>
    {
        public StateListResult e(final Status status) {
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
        public Status f(final Status status) {
            return status;
        }
    }
    
    private abstract static class e extends a<StateResult>
    {
        public StateResult g(final Status status) {
            return a(status);
        }
    }
}
