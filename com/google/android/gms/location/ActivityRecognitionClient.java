// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.RemoteException;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.internal.ly;
import com.google.android.gms.common.GooglePlayServicesClient;

@Deprecated
public class ActivityRecognitionClient implements GooglePlayServicesClient
{
    private final ly adP;
    
    public ActivityRecognitionClient(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
        this.adP = new ly(context, connectionCallbacks, onConnectionFailedListener, "activity_recognition");
    }
    
    @Override
    public void connect() {
        this.adP.connect();
    }
    
    @Override
    public void disconnect() {
        this.adP.disconnect();
    }
    
    @Override
    public boolean isConnected() {
        return this.adP.isConnected();
    }
    
    @Override
    public boolean isConnecting() {
        return this.adP.isConnecting();
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(final ConnectionCallbacks connectionCallbacks) {
        return this.adP.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener onConnectionFailedListener) {
        return this.adP.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    @Override
    public void registerConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.adP.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.adP.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    public void removeActivityUpdates(final PendingIntent pendingIntent) {
        try {
            this.adP.removeActivityUpdates(pendingIntent);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void requestActivityUpdates(final long n, final PendingIntent pendingIntent) {
        try {
            this.adP.requestActivityUpdates(n, pendingIntent);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    @Override
    public void unregisterConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.adP.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void unregisterConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.adP.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
}
