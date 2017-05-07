// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.GooglePlayServicesClient;
import android.os.Bundle;
import android.os.Handler;
import java.util.Collection;
import com.google.android.gms.common.internal.ClientSettings;
import java.util.List;
import com.google.android.gms.common.internal.n;
import java.util.HashMap;
import java.util.HashSet;
import android.content.Context;
import java.util.Map;
import android.view.View;
import java.util.Set;
import android.support.v4.app.FragmentActivity;
import android.os.Looper;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.ConnectionResult;

public interface GoogleApiClient
{
     <C extends Api.a> C a(final Api.c<C> p0);
    
     <A extends Api.a, R extends Result, T extends BaseImplementation.a<R, A>> T a(final T p0);
    
    boolean a(final Scope p0);
    
     <A extends Api.a, T extends BaseImplementation.a<? extends Result, A>> T b(final T p0);
    
    ConnectionResult blockingConnect();
    
    ConnectionResult blockingConnect(final long p0, final TimeUnit p1);
    
     <L> c<L> c(final L p0);
    
    void connect();
    
    void disconnect();
    
    Looper getLooper();
    
    boolean isConnected();
    
    boolean isConnecting();
    
    boolean isConnectionCallbacksRegistered(final ConnectionCallbacks p0);
    
    boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener p0);
    
    void reconnect();
    
    void registerConnectionCallbacks(final ConnectionCallbacks p0);
    
    void registerConnectionFailedListener(final OnConnectionFailedListener p0);
    
    void stopAutoManage(final FragmentActivity p0);
    
    void unregisterConnectionCallbacks(final ConnectionCallbacks p0);
    
    void unregisterConnectionFailedListener(final OnConnectionFailedListener p0);
    
    public static final class Builder
    {
        private String Dd;
        private Looper IB;
        private final Set<String> IE;
        private int IF;
        private View IG;
        private String IH;
        private final Map<Api<?>, Api.ApiOptions> II;
        private FragmentActivity IJ;
        private int IK;
        private OnConnectionFailedListener IL;
        private final Set<ConnectionCallbacks> IM;
        private final Set<OnConnectionFailedListener> IN;
        private final Context mContext;
        
        public Builder(final Context mContext) {
            this.IE = new HashSet<String>();
            this.II = new HashMap<Api<?>, Api.ApiOptions>();
            this.IK = -1;
            this.IM = new HashSet<ConnectionCallbacks>();
            this.IN = new HashSet<OnConnectionFailedListener>();
            this.mContext = mContext;
            this.IB = mContext.getMainLooper();
            this.IH = mContext.getPackageName();
        }
        
        public Builder(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            n.b(connectionCallbacks, "Must provide a connected listener");
            this.IM.add(connectionCallbacks);
            n.b(onConnectionFailedListener, "Must provide a connection failed listener");
            this.IN.add(onConnectionFailedListener);
        }
        
        private GoogleApiClient gm() {
            final d a = d.a(this.IJ);
            GoogleApiClient ak;
            if ((ak = a.ak(this.IK)) == null) {
                ak = new b(this.mContext.getApplicationContext(), this.IB, this.gl(), this.II, this.IM, this.IN, this.IK);
            }
            a.a(this.IK, ak, this.IL);
            return ak;
        }
        
        public Builder addApi(final Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            this.II.put(api, null);
            final List<Scope> ge = api.ge();
            for (int size = ge.size(), i = 0; i < size; ++i) {
                this.IE.add(ge.get(i).gt());
            }
            return this;
        }
        
        public <O extends Api.ApiOptions.HasOptions> Builder addApi(final Api<O> api, final O o) {
            n.b(o, "Null options are not permitted for this Api");
            this.II.put(api, (Api.ApiOptions)o);
            final List<Scope> ge = api.ge();
            for (int size = ge.size(), i = 0; i < size; ++i) {
                this.IE.add(ge.get(i).gt());
            }
            return this;
        }
        
        public Builder addConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
            this.IM.add(connectionCallbacks);
            return this;
        }
        
        public Builder addOnConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
            this.IN.add(onConnectionFailedListener);
            return this;
        }
        
        public Builder addScope(final Scope scope) {
            this.IE.add(scope.gt());
            return this;
        }
        
        public GoogleApiClient build() {
            n.b(!this.II.isEmpty(), (Object)"must call addApi() to add at least one API");
            if (this.IK >= 0) {
                return this.gm();
            }
            return new b(this.mContext, this.IB, this.gl(), this.II, this.IM, this.IN, -1);
        }
        
        public Builder enableAutoManage(final FragmentActivity fragmentActivity, final int ik, final OnConnectionFailedListener il) {
            n.b(ik >= 0, (Object)"clientId must be non-negative");
            this.IK = ik;
            this.IJ = n.b(fragmentActivity, "Null activity is not permitted.");
            this.IL = il;
            return this;
        }
        
        public ClientSettings gl() {
            return new ClientSettings(this.Dd, this.IE, this.IF, this.IG, this.IH);
        }
        
        public Builder setAccountName(final String dd) {
            this.Dd = dd;
            return this;
        }
        
        public Builder setGravityForPopups(final int if1) {
            this.IF = if1;
            return this;
        }
        
        public Builder setHandler(final Handler handler) {
            n.b(handler, "Handler must not be null");
            this.IB = handler.getLooper();
            return this;
        }
        
        public Builder setViewForPopups(final View ig) {
            this.IG = ig;
            return this;
        }
        
        public Builder useDefaultAccount() {
            return this.setAccountName("<<default account>>");
        }
    }
    
    public interface ConnectionCallbacks
    {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;
        
        void onConnected(final Bundle p0);
        
        void onConnectionSuspended(final int p0);
    }
    
    public interface OnConnectionFailedListener extends GooglePlayServicesClient.OnConnectionFailedListener
    {
        void onConnectionFailed(final ConnectionResult p0);
    }
}
