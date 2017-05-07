// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.internal.hc;
import com.google.android.gms.common.GooglePlayServicesClient;

public class ActivityRecognitionClient implements GooglePlayServicesClient
{
    private final hc NO;
    
    public ActivityRecognitionClient(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
        this.NO = new hc(context, connectionCallbacks, onConnectionFailedListener, "activity_recognition");
    }
    
    @Override
    public void connect() {
        this.NO.connect();
    }
    
    @Override
    public void disconnect() {
        this.NO.disconnect();
    }
    
    @Override
    public boolean isConnected() {
        return this.NO.isConnected();
    }
    
    @Override
    public boolean isConnecting() {
        return this.NO.isConnecting();
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(final ConnectionCallbacks connectionCallbacks) {
        return this.NO.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener onConnectionFailedListener) {
        return this.NO.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    @Override
    public void registerConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.NO.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.NO.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    public void removeActivityUpdates(final PendingIntent pendingIntent) {
        this.NO.removeActivityUpdates(pendingIntent);
    }
    
    public void requestActivityUpdates(final long n, final PendingIntent pendingIntent) {
        this.NO.requestActivityUpdates(n, pendingIntent);
    }
    
    @Override
    public void unregisterConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.NO.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void unregisterConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.NO.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
}
