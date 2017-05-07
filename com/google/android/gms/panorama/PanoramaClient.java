// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.panorama;

import android.content.Intent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.a;
import android.net.Uri;
import android.content.Context;
import com.google.android.gms.internal.hm;
import com.google.android.gms.common.GooglePlayServicesClient;

public class PanoramaClient implements GooglePlayServicesClient
{
    private final hm Di;
    
    public PanoramaClient(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
        this.Di = new hm(context, connectionCallbacks, onConnectionFailedListener);
    }
    
    @Override
    public void connect() {
        this.Di.connect();
    }
    
    @Override
    public void disconnect() {
        this.Di.disconnect();
    }
    
    @Override
    public boolean isConnected() {
        return this.Di.isConnected();
    }
    
    @Override
    public boolean isConnecting() {
        return this.Di.isConnecting();
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(final ConnectionCallbacks connectionCallbacks) {
        return this.Di.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener onConnectionFailedListener) {
        return this.Di.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    public void loadPanoramaInfo(final OnPanoramaInfoLoadedListener onPanoramaInfoLoadedListener, final Uri uri) {
        this.Di.a(new a.c<Panorama.PanoramaResult>() {
            public void a(final Panorama.PanoramaResult panoramaResult) {
                onPanoramaInfoLoadedListener.onPanoramaInfoLoaded(panoramaResult.getStatus().bu(), panoramaResult.getViewerIntent());
            }
        }, uri, false);
    }
    
    public void loadPanoramaInfoAndGrantAccess(final OnPanoramaInfoLoadedListener onPanoramaInfoLoadedListener, final Uri uri) {
        this.Di.a(new a.c<Panorama.PanoramaResult>() {
            public void a(final Panorama.PanoramaResult panoramaResult) {
                onPanoramaInfoLoadedListener.onPanoramaInfoLoaded(panoramaResult.getStatus().bu(), panoramaResult.getViewerIntent());
            }
        }, uri, true);
    }
    
    @Override
    public void registerConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.Di.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.Di.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Override
    public void unregisterConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.Di.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void unregisterConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.Di.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
    
    public interface OnPanoramaInfoLoadedListener
    {
        void onPanoramaInfoLoaded(final ConnectionResult p0, final Intent p1);
    }
}
