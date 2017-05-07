// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.IBinder;
import java.util.List;
import android.content.Context;
import java.util.ArrayList;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.Intent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.util.Log;
import android.os.Message;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.util.SparseArray;
import android.os.IBinder$DeathRecipient;

final class RegisteredMediaRouteProvider$Connection implements IBinder$DeathRecipient
{
    private int mNextControllerId;
    private int mNextRequestId;
    private final SparseArray<MediaRouter$ControlRequestCallback> mPendingCallbacks;
    private int mPendingRegisterRequestId;
    private final RegisteredMediaRouteProvider$ReceiveHandler mReceiveHandler;
    private final Messenger mReceiveMessenger;
    private final Messenger mServiceMessenger;
    private int mServiceVersion;
    final /* synthetic */ RegisteredMediaRouteProvider this$0;
    
    public RegisteredMediaRouteProvider$Connection(final RegisteredMediaRouteProvider this$0, final Messenger mServiceMessenger) {
        this.this$0 = this$0;
        this.mNextRequestId = 1;
        this.mNextControllerId = 1;
        this.mPendingCallbacks = (SparseArray<MediaRouter$ControlRequestCallback>)new SparseArray();
        this.mServiceMessenger = mServiceMessenger;
        this.mReceiveHandler = new RegisteredMediaRouteProvider$ReceiveHandler(this);
        this.mReceiveMessenger = new Messenger((Handler)this.mReceiveHandler);
    }
    
    private void failPendingCallbacks() {
        for (int i = 0; i < this.mPendingCallbacks.size(); ++i) {
            ((MediaRouter$ControlRequestCallback)this.mPendingCallbacks.valueAt(i)).onError(null, null);
        }
        this.mPendingCallbacks.clear();
    }
    
    private boolean sendRequest(final int what, final int arg1, final int arg2, final Object obj, final Bundle data) {
        final Message obtain = Message.obtain();
        obtain.what = what;
        obtain.arg1 = arg1;
        obtain.arg2 = arg2;
        obtain.obj = obj;
        obtain.setData(data);
        obtain.replyTo = this.mReceiveMessenger;
        try {
            this.mServiceMessenger.send(obtain);
            return true;
        }
        catch (RemoteException ex) {
            if (what == 2) {
                goto Label_0074;
            }
            Log.e("MediaRouteProviderProxy", "Could not send message to service.", (Throwable)ex);
        }
        catch (DeadObjectException ex2) {
            goto Label_0074;
        }
    }
    
    public void binderDied() {
        this.this$0.mPrivateHandler.post((Runnable)new RegisteredMediaRouteProvider$Connection$2(this));
    }
    
    public int createRouteController(final String s) {
        final int n = this.mNextControllerId++;
        final Bundle bundle = new Bundle();
        bundle.putString("routeId", s);
        this.sendRequest(3, this.mNextRequestId++, n, null, bundle);
        return n;
    }
    
    public void dispose() {
        this.sendRequest(2, 0, 0, null, null);
        this.mReceiveHandler.dispose();
        this.mServiceMessenger.getBinder().unlinkToDeath((IBinder$DeathRecipient)this, 0);
        this.this$0.mPrivateHandler.post((Runnable)new RegisteredMediaRouteProvider$Connection$1(this));
    }
    
    public boolean onControlRequestFailed(final int n, final String s, final Bundle bundle) {
        final MediaRouter$ControlRequestCallback mediaRouter$ControlRequestCallback = (MediaRouter$ControlRequestCallback)this.mPendingCallbacks.get(n);
        if (mediaRouter$ControlRequestCallback != null) {
            this.mPendingCallbacks.remove(n);
            mediaRouter$ControlRequestCallback.onError(s, bundle);
            return true;
        }
        return false;
    }
    
    public boolean onControlRequestSucceeded(final int n, final Bundle bundle) {
        final MediaRouter$ControlRequestCallback mediaRouter$ControlRequestCallback = (MediaRouter$ControlRequestCallback)this.mPendingCallbacks.get(n);
        if (mediaRouter$ControlRequestCallback != null) {
            this.mPendingCallbacks.remove(n);
            mediaRouter$ControlRequestCallback.onResult(bundle);
            return true;
        }
        return false;
    }
    
    public boolean onDescriptorChanged(final Bundle bundle) {
        if (this.mServiceVersion != 0) {
            this.this$0.onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(bundle));
            return true;
        }
        return false;
    }
    
    public boolean onGenericFailure(final int n) {
        if (n == this.mPendingRegisterRequestId) {
            this.mPendingRegisterRequestId = 0;
            this.this$0.onConnectionError(this, "Registation failed");
        }
        final MediaRouter$ControlRequestCallback mediaRouter$ControlRequestCallback = (MediaRouter$ControlRequestCallback)this.mPendingCallbacks.get(n);
        if (mediaRouter$ControlRequestCallback != null) {
            this.mPendingCallbacks.remove(n);
            mediaRouter$ControlRequestCallback.onError(null, null);
        }
        return true;
    }
    
    public boolean onGenericSuccess(final int n) {
        return true;
    }
    
    public boolean onRegistered(final int n, final int mServiceVersion, final Bundle bundle) {
        if (this.mServiceVersion == 0 && n == this.mPendingRegisterRequestId && mServiceVersion >= 1) {
            this.mPendingRegisterRequestId = 0;
            this.mServiceVersion = mServiceVersion;
            this.this$0.onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(bundle));
            this.this$0.onConnectionReady(this);
            return true;
        }
        return false;
    }
    
    public boolean register() {
        this.mPendingRegisterRequestId = this.mNextRequestId++;
        if (!this.sendRequest(1, this.mPendingRegisterRequestId, 1, null, null)) {
            return false;
        }
        try {
            this.mServiceMessenger.getBinder().linkToDeath((IBinder$DeathRecipient)this, 0);
            return true;
        }
        catch (RemoteException ex) {
            this.binderDied();
            return false;
        }
    }
    
    public void releaseRouteController(final int n) {
        this.sendRequest(4, this.mNextRequestId++, n, null, null);
    }
    
    public void selectRoute(final int n) {
        this.sendRequest(5, this.mNextRequestId++, n, null, null);
    }
    
    public boolean sendControlRequest(final int n, final Intent intent, final MediaRouter$ControlRequestCallback mediaRouter$ControlRequestCallback) {
        final int n2 = this.mNextRequestId++;
        if (this.sendRequest(9, n2, n, intent, null)) {
            if (mediaRouter$ControlRequestCallback != null) {
                this.mPendingCallbacks.put(n2, (Object)mediaRouter$ControlRequestCallback);
            }
            return true;
        }
        return false;
    }
    
    public void setDiscoveryRequest(final MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
        final int n = this.mNextRequestId++;
        Bundle bundle;
        if (mediaRouteDiscoveryRequest != null) {
            bundle = mediaRouteDiscoveryRequest.asBundle();
        }
        else {
            bundle = null;
        }
        this.sendRequest(10, n, 0, bundle, null);
    }
    
    public void setVolume(final int n, int n2) {
        final Bundle bundle = new Bundle();
        bundle.putInt("volume", n2);
        n2 = this.mNextRequestId++;
        this.sendRequest(7, n2, n, null, bundle);
    }
    
    public void unselectRoute(final int n) {
        this.sendRequest(6, this.mNextRequestId++, n, null, null);
    }
    
    public void updateVolume(final int n, int n2) {
        final Bundle bundle = new Bundle();
        bundle.putInt("volume", n2);
        n2 = this.mNextRequestId++;
        this.sendRequest(8, n2, n, null, bundle);
    }
}
