// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.internal.gn;
import com.google.android.gms.common.GooglePlayServicesClient;

public class ActivityRecognitionClient implements GooglePlayServicesClient
{
    private final gn xl;
    
    public ActivityRecognitionClient(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
        this.xl = new gn(context, connectionCallbacks, onConnectionFailedListener, "activity_recognition");
    }
    
    @Override
    public void connect() {
        this.xl.connect();
    }
    
    @Override
    public void disconnect() {
        this.xl.disconnect();
    }
    
    @Override
    public boolean isConnected() {
        return this.xl.isConnected();
    }
    
    @Override
    public boolean isConnecting() {
        return this.xl.isConnecting();
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(final ConnectionCallbacks connectionCallbacks) {
        return this.xl.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener onConnectionFailedListener) {
        return this.xl.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    @Override
    public void registerConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.xl.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.xl.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    public void removeActivityUpdates(final PendingIntent pendingIntent) {
        this.xl.removeActivityUpdates(pendingIntent);
    }
    
    public void requestActivityUpdates(final long n, final PendingIntent pendingIntent) {
        this.xl.requestActivityUpdates(n, pendingIntent);
    }
    
    @Override
    public void unregisterConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.xl.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void unregisterConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.xl.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
}
