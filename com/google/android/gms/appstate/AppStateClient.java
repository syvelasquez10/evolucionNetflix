// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.internal.ds;
import com.google.android.gms.internal.eg;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import android.content.Context;
import com.google.android.gms.internal.dc;
import com.google.android.gms.common.GooglePlayServicesClient;

@Deprecated
public final class AppStateClient implements GooglePlayServicesClient
{
    public static final int STATUS_CLIENT_RECONNECT_REQUIRED = 2;
    public static final int STATUS_DEVELOPER_ERROR = 7;
    public static final int STATUS_INTERNAL_ERROR = 1;
    public static final int STATUS_NETWORK_ERROR_NO_DATA = 4;
    public static final int STATUS_NETWORK_ERROR_OPERATION_DEFERRED = 5;
    public static final int STATUS_NETWORK_ERROR_OPERATION_FAILED = 6;
    public static final int STATUS_NETWORK_ERROR_STALE_DATA = 3;
    public static final int STATUS_OK = 0;
    public static final int STATUS_STATE_KEY_LIMIT_EXCEEDED = 2003;
    public static final int STATUS_STATE_KEY_NOT_FOUND = 2002;
    public static final int STATUS_WRITE_OUT_OF_DATE_VERSION = 2000;
    public static final int STATUS_WRITE_SIZE_EXCEEDED = 2001;
    private final dc jx;
    
    private AppStateClient(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener, final String s, final String[] array) {
        this.jx = new dc(context, connectionCallbacks, onConnectionFailedListener, s, array);
    }
    
    @Deprecated
    @Override
    public void connect() {
        this.jx.connect();
    }
    
    @Deprecated
    public void deleteState(final OnStateDeletedListener onStateDeletedListener, final int n) {
        this.jx.a(new com.google.android.gms.common.api.a.c<AppStateManager.StateDeletedResult>() {
            public void a(final AppStateManager.StateDeletedResult stateDeletedResult) {
                onStateDeletedListener.onStateDeleted(stateDeletedResult.getStatus().getStatusCode(), stateDeletedResult.getStateKey());
            }
        }, n);
    }
    
    @Deprecated
    @Override
    public void disconnect() {
        this.jx.disconnect();
    }
    
    @Deprecated
    public int getMaxNumKeys() {
        return this.jx.getMaxNumKeys();
    }
    
    @Deprecated
    public int getMaxStateSize() {
        return this.jx.getMaxStateSize();
    }
    
    @Deprecated
    @Override
    public boolean isConnected() {
        return this.jx.isConnected();
    }
    
    @Deprecated
    @Override
    public boolean isConnecting() {
        return this.jx.isConnecting();
    }
    
    @Deprecated
    @Override
    public boolean isConnectionCallbacksRegistered(final ConnectionCallbacks connectionCallbacks) {
        return this.jx.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener onConnectionFailedListener) {
        return this.jx.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    @Deprecated
    public void listStates(final OnStateListLoadedListener onStateListLoadedListener) {
        this.jx.a(new com.google.android.gms.common.api.a.c<AppStateManager.StateListResult>() {
            public void a(final AppStateManager.StateListResult stateListResult) {
                onStateListLoadedListener.onStateListLoaded(stateListResult.getStatus().getStatusCode(), stateListResult.getStateBuffer());
            }
        });
    }
    
    @Deprecated
    public void loadState(final OnStateLoadedListener onStateLoadedListener, final int n) {
        this.jx.b(new a(onStateLoadedListener), n);
    }
    
    @Deprecated
    public void reconnect() {
        this.jx.disconnect();
        this.jx.connect();
    }
    
    @Deprecated
    @Override
    public void registerConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.jx.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public void registerConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.jx.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Deprecated
    public void resolveState(final OnStateLoadedListener onStateLoadedListener, final int n, final String s, final byte[] array) {
        this.jx.a(new a(onStateLoadedListener), n, s, array);
    }
    
    @Deprecated
    public void signOut() {
        this.jx.b(new com.google.android.gms.common.api.a.c<Status>() {
            public void a(final Status status) {
            }
        });
    }
    
    @Deprecated
    public void signOut(final OnSignOutCompleteListener onSignOutCompleteListener) {
        eg.b(onSignOutCompleteListener, "Must provide a valid listener");
        this.jx.b(new com.google.android.gms.common.api.a.c<Status>() {
            public void a(final Status status) {
                onSignOutCompleteListener.onSignOutComplete();
            }
        });
    }
    
    @Deprecated
    @Override
    public void unregisterConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.jx.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public void unregisterConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.jx.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Deprecated
    public void updateState(final int n, final byte[] array) {
        this.jx.a(new a(null), n, array);
    }
    
    @Deprecated
    public void updateStateImmediate(final OnStateLoadedListener onStateLoadedListener, final int n, final byte[] array) {
        eg.b(onStateLoadedListener, "Must provide a valid listener");
        this.jx.a(new a(onStateLoadedListener), n, array);
    }
    
    @Deprecated
    public static final class Builder
    {
        private static final String[] jC;
        private ConnectionCallbacks jD;
        private OnConnectionFailedListener jE;
        private String[] jF;
        private String jG;
        private Context mContext;
        
        static {
            jC = new String[] { "https://www.googleapis.com/auth/appstate" };
        }
        
        public Builder(final Context mContext, final ConnectionCallbacks jd, final OnConnectionFailedListener je) {
            this.mContext = mContext;
            this.jD = jd;
            this.jE = je;
            this.jF = Builder.jC;
            this.jG = "<<default account>>";
        }
        
        public AppStateClient create() {
            return new AppStateClient(this.mContext, this.jD, this.jE, this.jG, this.jF, null);
        }
        
        public Builder setAccountName(final String s) {
            this.jG = eg.f(s);
            return this;
        }
        
        public Builder setScopes(final String... jf) {
            this.jF = jf;
            return this;
        }
    }
    
    private static final class a implements c<AppStateManager.StateResult>
    {
        private final OnStateLoadedListener jH;
        
        a(final OnStateLoadedListener jh) {
            this.jH = jh;
        }
        
        public void a(final AppStateManager.StateResult stateResult) {
            if (this.jH == null) {
                return;
            }
            if (stateResult.getStatus().getStatusCode() == 2000) {
                final AppStateManager.StateConflictResult conflictResult = stateResult.getConflictResult();
                ds.d(conflictResult);
                this.jH.onStateConflict(conflictResult.getStateKey(), conflictResult.getResolvedVersion(), conflictResult.getLocalData(), conflictResult.getServerData());
                return;
            }
            final AppStateManager.StateLoadedResult loadedResult = stateResult.getLoadedResult();
            ds.d(loadedResult);
            this.jH.onStateLoaded(loadedResult.getStatus().getStatusCode(), loadedResult.getStateKey(), loadedResult.getLocalData());
        }
    }
}
