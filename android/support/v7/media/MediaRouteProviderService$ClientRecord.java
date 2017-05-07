// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.IBinder;
import android.os.DeadObjectException;
import android.os.Message;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import android.app.Service;
import android.os.RemoteException;
import android.os.Messenger;
import android.util.SparseArray;
import android.os.IBinder$DeathRecipient;

final class MediaRouteProviderService$ClientRecord implements IBinder$DeathRecipient
{
    private final SparseArray<MediaRouteProvider$RouteController> mControllers;
    public MediaRouteDiscoveryRequest mDiscoveryRequest;
    public final Messenger mMessenger;
    public final int mVersion;
    final /* synthetic */ MediaRouteProviderService this$0;
    
    public MediaRouteProviderService$ClientRecord(final MediaRouteProviderService this$0, final Messenger mMessenger, final int mVersion) {
        this.this$0 = this$0;
        this.mControllers = (SparseArray<MediaRouteProvider$RouteController>)new SparseArray();
        this.mMessenger = mMessenger;
        this.mVersion = mVersion;
    }
    
    public void binderDied() {
        this.this$0.mPrivateHandler.obtainMessage(1, (Object)this.mMessenger).sendToTarget();
    }
    
    public boolean createRouteController(final String s, final int n) {
        if (this.mControllers.indexOfKey(n) < 0) {
            final MediaRouteProvider$RouteController onCreateRouteController = this.this$0.mProvider.onCreateRouteController(s);
            if (onCreateRouteController != null) {
                this.mControllers.put(n, (Object)onCreateRouteController);
                return true;
            }
        }
        return false;
    }
    
    public void dispose() {
        for (int size = this.mControllers.size(), i = 0; i < size; ++i) {
            ((MediaRouteProvider$RouteController)this.mControllers.valueAt(i)).onRelease();
        }
        this.mControllers.clear();
        this.mMessenger.getBinder().unlinkToDeath((IBinder$DeathRecipient)this, 0);
        this.setDiscoveryRequest(null);
    }
    
    public MediaRouteProvider$RouteController getRouteController(final int n) {
        return (MediaRouteProvider$RouteController)this.mControllers.get(n);
    }
    
    public boolean hasMessenger(final Messenger messenger) {
        return this.mMessenger.getBinder() == messenger.getBinder();
    }
    
    public boolean register() {
        try {
            this.mMessenger.getBinder().linkToDeath((IBinder$DeathRecipient)this, 0);
            return true;
        }
        catch (RemoteException ex) {
            this.binderDied();
            return false;
        }
    }
    
    public boolean releaseRouteController(final int n) {
        final MediaRouteProvider$RouteController mediaRouteProvider$RouteController = (MediaRouteProvider$RouteController)this.mControllers.get(n);
        if (mediaRouteProvider$RouteController != null) {
            this.mControllers.remove(n);
            mediaRouteProvider$RouteController.onRelease();
            return true;
        }
        return false;
    }
    
    public boolean setDiscoveryRequest(final MediaRouteDiscoveryRequest mDiscoveryRequest) {
        if (this.mDiscoveryRequest != mDiscoveryRequest && (this.mDiscoveryRequest == null || !this.mDiscoveryRequest.equals(mDiscoveryRequest))) {
            this.mDiscoveryRequest = mDiscoveryRequest;
            return this.this$0.updateCompositeDiscoveryRequest();
        }
        return false;
    }
    
    @Override
    public String toString() {
        return getClientId(this.mMessenger);
    }
}
