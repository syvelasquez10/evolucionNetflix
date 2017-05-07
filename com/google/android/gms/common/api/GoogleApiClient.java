// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.GooglePlayServicesClient;
import android.os.Bundle;
import android.os.Handler;
import java.util.Collection;
import com.google.android.gms.internal.fc;
import java.util.List;
import com.google.android.gms.internal.fq;
import java.util.HashMap;
import java.util.HashSet;
import android.content.Context;
import java.util.Map;
import android.view.View;
import java.util.Set;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.TimeUnit;

public interface GoogleApiClient
{
     <C extends Api.a> C a(final Api.c<C> p0);
    
     <A extends Api.a, T extends a.b<? extends Result, A>> T a(final T p0);
    
     <A extends Api.a, T extends a.b<? extends Result, A>> T b(final T p0);
    
    ConnectionResult blockingConnect(final long p0, final TimeUnit p1);
    
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
    
    void unregisterConnectionCallbacks(final ConnectionCallbacks p0);
    
    void unregisterConnectionFailedListener(final OnConnectionFailedListener p0);
    
    public static final class Builder
    {
        private Looper AS;
        private final Set<String> AT;
        private int AU;
        private View AV;
        private String AW;
        private final Map<Api<?>, Api.ApiOptions> AX;
        private final Set<ConnectionCallbacks> AY;
        private final Set<OnConnectionFailedListener> AZ;
        private final Context mContext;
        private String wG;
        
        public Builder(final Context mContext) {
            this.AT = new HashSet<String>();
            this.AX = new HashMap<Api<?>, Api.ApiOptions>();
            this.AY = new HashSet<ConnectionCallbacks>();
            this.AZ = new HashSet<OnConnectionFailedListener>();
            this.mContext = mContext;
            this.AS = mContext.getMainLooper();
            this.AW = mContext.getPackageName();
        }
        
        public Builder(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            fq.b(connectionCallbacks, "Must provide a connected listener");
            this.AY.add(connectionCallbacks);
            fq.b(onConnectionFailedListener, "Must provide a connection failed listener");
            this.AZ.add(onConnectionFailedListener);
        }
        
        public Builder addApi(final Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            this.AX.put(api, null);
            final List<Scope> dz = api.dZ();
            for (int size = dz.size(), i = 0; i < size; ++i) {
                this.AT.add(dz.get(i).en());
            }
            return this;
        }
        
        public <O extends Api.ApiOptions.HasOptions> Builder addApi(final Api<O> api, final O o) {
            fq.b(o, "Null options are not permitted for this Api");
            this.AX.put(api, (Api.ApiOptions)o);
            final List<Scope> dz = api.dZ();
            for (int size = dz.size(), i = 0; i < size; ++i) {
                this.AT.add(dz.get(i).en());
            }
            return this;
        }
        
        public Builder addConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
            this.AY.add(connectionCallbacks);
            return this;
        }
        
        public Builder addOnConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
            this.AZ.add(onConnectionFailedListener);
            return this;
        }
        
        public Builder addScope(final Scope scope) {
            this.AT.add(scope.en());
            return this;
        }
        
        public GoogleApiClient build() {
            return new b(this.mContext, this.AS, this.eh(), this.AX, this.AY, this.AZ);
        }
        
        public fc eh() {
            return new fc(this.wG, this.AT, this.AU, this.AV, this.AW);
        }
        
        public Builder setAccountName(final String wg) {
            this.wG = wg;
            return this;
        }
        
        public Builder setGravityForPopups(final int au) {
            this.AU = au;
            return this;
        }
        
        public Builder setHandler(final Handler handler) {
            fq.b(handler, "Handler must not be null");
            this.AS = handler.getLooper();
            return this;
        }
        
        public Builder setViewForPopups(final View av) {
            this.AV = av;
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
