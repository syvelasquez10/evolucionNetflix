// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.RemoteException;
import android.app.PendingIntent;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesClient$ConnectionCallbacks;
import android.content.Context;
import com.google.android.gms.internal.ly;
import com.google.android.gms.common.GooglePlayServicesClient;

@Deprecated
public class ActivityRecognitionClient implements GooglePlayServicesClient
{
    private final ly adP;
    
    public ActivityRecognitionClient(final Context context, final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks, final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener) {
        this.adP = new ly(context, googlePlayServicesClient$ConnectionCallbacks, googlePlayServicesClient$OnConnectionFailedListener, "activity_recognition");
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
    public boolean isConnectionCallbacksRegistered(final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks) {
        return this.adP.isConnectionCallbacksRegistered(googlePlayServicesClient$ConnectionCallbacks);
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener) {
        return this.adP.isConnectionFailedListenerRegistered(googlePlayServicesClient$OnConnectionFailedListener);
    }
    
    @Override
    public void registerConnectionCallbacks(final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks) {
        this.adP.registerConnectionCallbacks(googlePlayServicesClient$ConnectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener) {
        this.adP.registerConnectionFailedListener(googlePlayServicesClient$OnConnectionFailedListener);
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
    public void unregisterConnectionCallbacks(final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks) {
        this.adP.unregisterConnectionCallbacks(googlePlayServicesClient$ConnectionCallbacks);
    }
    
    @Override
    public void unregisterConnectionFailedListener(final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener) {
        this.adP.unregisterConnectionFailedListener(googlePlayServicesClient$OnConnectionFailedListener);
    }
}
