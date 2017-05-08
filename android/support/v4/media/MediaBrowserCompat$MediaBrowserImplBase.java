// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Binder;
import android.support.v4.os.BuildCompat;
import android.os.Build$VERSION;
import java.lang.ref.WeakReference;
import android.os.IBinder;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import android.support.v4.os.ResultReceiver;
import android.os.Handler;
import android.text.TextUtils;
import android.os.RemoteException;
import android.content.ServiceConnection;
import android.content.Intent;
import android.util.Log;
import android.support.v4.util.ArrayMap;
import android.content.ComponentName;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.os.Bundle;
import android.content.Context;
import android.os.Messenger;

class MediaBrowserCompat$MediaBrowserImplBase implements MediaBrowserCompat$MediaBrowserImpl, MediaBrowserCompat$MediaBrowserServiceCallbackImpl
{
    private static final int CONNECT_STATE_CONNECTED = 2;
    static final int CONNECT_STATE_CONNECTING = 1;
    static final int CONNECT_STATE_DISCONNECTED = 0;
    static final int CONNECT_STATE_SUSPENDED = 3;
    final MediaBrowserCompat$ConnectionCallback mCallback;
    Messenger mCallbacksMessenger;
    final Context mContext;
    private Bundle mExtras;
    final MediaBrowserCompat$CallbackHandler mHandler;
    private MediaSessionCompat$Token mMediaSessionToken;
    final Bundle mRootHints;
    private String mRootId;
    MediaBrowserCompat$ServiceBinderWrapper mServiceBinderWrapper;
    final ComponentName mServiceComponent;
    MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection mServiceConnection;
    int mState;
    private final ArrayMap<String, MediaBrowserCompat$Subscription> mSubscriptions;
    
    public MediaBrowserCompat$MediaBrowserImplBase(final Context mContext, final ComponentName mServiceComponent, final MediaBrowserCompat$ConnectionCallback mCallback, final Bundle bundle) {
        this.mHandler = new MediaBrowserCompat$CallbackHandler(this);
        this.mSubscriptions = new ArrayMap<String, MediaBrowserCompat$Subscription>();
        this.mState = 0;
        if (mContext == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        if (mServiceComponent == null) {
            throw new IllegalArgumentException("service component must not be null");
        }
        if (mCallback == null) {
            throw new IllegalArgumentException("connection callback must not be null");
        }
        this.mContext = mContext;
        this.mServiceComponent = mServiceComponent;
        this.mCallback = mCallback;
        Bundle mRootHints;
        if (bundle == null) {
            mRootHints = null;
        }
        else {
            mRootHints = new Bundle(bundle);
        }
        this.mRootHints = mRootHints;
    }
    
    private static String getStateLabel(final int n) {
        switch (n) {
            default: {
                return "UNKNOWN/" + n;
            }
            case 0: {
                return "CONNECT_STATE_DISCONNECTED";
            }
            case 1: {
                return "CONNECT_STATE_CONNECTING";
            }
            case 2: {
                return "CONNECT_STATE_CONNECTED";
            }
            case 3: {
                return "CONNECT_STATE_SUSPENDED";
            }
        }
    }
    
    private boolean isCurrent(final Messenger messenger, final String s) {
        if (this.mCallbacksMessenger != messenger) {
            if (this.mState != 0) {
                Log.i("MediaBrowserCompat", s + " for " + this.mServiceComponent + " with mCallbacksMessenger=" + this.mCallbacksMessenger + " this=" + this);
            }
            return false;
        }
        return true;
    }
    
    @Override
    public void connect() {
        if (this.mState != 0) {
            throw new IllegalStateException("connect() called while not disconnected (state=" + getStateLabel(this.mState) + ")");
        }
        if (MediaBrowserCompat.DEBUG && this.mServiceConnection != null) {
            throw new RuntimeException("mServiceConnection should be null. Instead it is " + this.mServiceConnection);
        }
        if (this.mServiceBinderWrapper != null) {
            throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + this.mServiceBinderWrapper);
        }
        if (this.mCallbacksMessenger != null) {
            throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + this.mCallbacksMessenger);
        }
        this.mState = 1;
        final Intent intent = new Intent("android.media.browse.MediaBrowserService");
        intent.setComponent(this.mServiceComponent);
        final MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection mServiceConnection = new MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection(this);
        this.mServiceConnection = mServiceConnection;
        int bindService = 0;
        while (true) {
            try {
                bindService = (this.mContext.bindService(intent, (ServiceConnection)this.mServiceConnection, 1) ? 1 : 0);
                if (bindService == 0) {
                    this.mHandler.post((Runnable)new MediaBrowserCompat$MediaBrowserImplBase$1(this, (ServiceConnection)mServiceConnection));
                }
                if (MediaBrowserCompat.DEBUG) {
                    Log.d("MediaBrowserCompat", "connect...");
                    this.dump();
                }
            }
            catch (Exception ex) {
                Log.e("MediaBrowserCompat", "Failed binding to service " + this.mServiceComponent);
                continue;
            }
            break;
        }
    }
    
    @Override
    public void disconnect() {
        while (true) {
            if (this.mCallbacksMessenger == null) {
                break Label_0018;
            }
            try {
                this.mServiceBinderWrapper.disconnect(this.mCallbacksMessenger);
                this.forceCloseConnection();
                if (MediaBrowserCompat.DEBUG) {
                    Log.d("MediaBrowserCompat", "disconnect...");
                    this.dump();
                }
            }
            catch (RemoteException ex) {
                Log.w("MediaBrowserCompat", "RemoteException during connect for " + this.mServiceComponent);
                continue;
            }
            break;
        }
    }
    
    void dump() {
        Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
        Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.mServiceComponent);
        Log.d("MediaBrowserCompat", "  mCallback=" + this.mCallback);
        Log.d("MediaBrowserCompat", "  mRootHints=" + this.mRootHints);
        Log.d("MediaBrowserCompat", "  mState=" + getStateLabel(this.mState));
        Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.mServiceConnection);
        Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.mServiceBinderWrapper);
        Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.mCallbacksMessenger);
        Log.d("MediaBrowserCompat", "  mRootId=" + this.mRootId);
        Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.mMediaSessionToken);
    }
    
    void forceCloseConnection() {
        if (this.mServiceConnection != null) {
            this.mContext.unbindService((ServiceConnection)this.mServiceConnection);
        }
        this.mState = 0;
        this.mServiceConnection = null;
        this.mServiceBinderWrapper = null;
        this.mCallbacksMessenger = null;
        this.mHandler.setCallbacksMessenger(null);
        this.mRootId = null;
        this.mMediaSessionToken = null;
    }
    
    @Override
    public Bundle getExtras() {
        if (!this.isConnected()) {
            throw new IllegalStateException("getExtras() called while not connected (state=" + getStateLabel(this.mState) + ")");
        }
        return this.mExtras;
    }
    
    @Override
    public void getItem(final String s, final MediaBrowserCompat$ItemCallback mediaBrowserCompat$ItemCallback) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("mediaId is empty");
        }
        if (mediaBrowserCompat$ItemCallback == null) {
            throw new IllegalArgumentException("cb is null");
        }
        if (this.mState != 2) {
            Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
            this.mHandler.post((Runnable)new MediaBrowserCompat$MediaBrowserImplBase$2(this, mediaBrowserCompat$ItemCallback, s));
            return;
        }
        final MediaBrowserCompat$ItemReceiver mediaBrowserCompat$ItemReceiver = new MediaBrowserCompat$ItemReceiver(s, mediaBrowserCompat$ItemCallback, this.mHandler);
        try {
            this.mServiceBinderWrapper.getMediaItem(s, mediaBrowserCompat$ItemReceiver, this.mCallbacksMessenger);
        }
        catch (RemoteException ex) {
            Log.i("MediaBrowserCompat", "Remote error getting media item.");
            this.mHandler.post((Runnable)new MediaBrowserCompat$MediaBrowserImplBase$3(this, mediaBrowserCompat$ItemCallback, s));
        }
    }
    
    @Override
    public String getRoot() {
        if (!this.isConnected()) {
            throw new IllegalStateException("getRoot() called while not connected(state=" + getStateLabel(this.mState) + ")");
        }
        return this.mRootId;
    }
    
    @Override
    public ComponentName getServiceComponent() {
        if (!this.isConnected()) {
            throw new IllegalStateException("getServiceComponent() called while not connected (state=" + this.mState + ")");
        }
        return this.mServiceComponent;
    }
    
    @Override
    public MediaSessionCompat$Token getSessionToken() {
        if (!this.isConnected()) {
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.mState + ")");
        }
        return this.mMediaSessionToken;
    }
    
    @Override
    public boolean isConnected() {
        return this.mState == 2;
    }
    
    @Override
    public void onConnectionFailed(final Messenger messenger) {
        Log.e("MediaBrowserCompat", "onConnectFailed for " + this.mServiceComponent);
        if (!this.isCurrent(messenger, "onConnectFailed")) {
            return;
        }
        if (this.mState != 1) {
            Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
            return;
        }
        this.forceCloseConnection();
        this.mCallback.onConnectionFailed();
    }
    
    @Override
    public void onLoadChildren(final Messenger messenger, final String s, final List list, final Bundle bundle) {
        if (this.isCurrent(messenger, "onLoadChildren")) {
            if (MediaBrowserCompat.DEBUG) {
                Log.d("MediaBrowserCompat", "onLoadChildren for " + this.mServiceComponent + " id=" + s);
            }
            final MediaBrowserCompat$Subscription mediaBrowserCompat$Subscription = this.mSubscriptions.get(s);
            if (mediaBrowserCompat$Subscription == null) {
                if (MediaBrowserCompat.DEBUG) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + s);
                }
            }
            else {
                final MediaBrowserCompat$SubscriptionCallback callback = mediaBrowserCompat$Subscription.getCallback(bundle);
                if (callback != null) {
                    if (bundle == null) {
                        callback.onChildrenLoaded(s, list);
                        return;
                    }
                    callback.onChildrenLoaded(s, list, bundle);
                }
            }
        }
    }
    
    @Override
    public void onServiceConnected(final Messenger messenger, String mRootId, final MediaSessionCompat$Token mMediaSessionToken, final Bundle mExtras) {
        if (this.isCurrent(messenger, "onConnect")) {
            if (this.mState != 1) {
                Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
                return;
            }
            this.mRootId = mRootId;
            this.mMediaSessionToken = mMediaSessionToken;
            this.mExtras = mExtras;
            this.mState = 2;
            if (MediaBrowserCompat.DEBUG) {
                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                this.dump();
            }
            this.mCallback.onConnected();
            try {
                for (final Map.Entry<String, MediaBrowserCompat$Subscription> entry : this.mSubscriptions.entrySet()) {
                    mRootId = entry.getKey();
                    final MediaBrowserCompat$Subscription mediaBrowserCompat$Subscription = entry.getValue();
                    final List<MediaBrowserCompat$SubscriptionCallback> callbacks = mediaBrowserCompat$Subscription.getCallbacks();
                    final List<Bundle> optionsList = mediaBrowserCompat$Subscription.getOptionsList();
                    for (int i = 0; i < callbacks.size(); ++i) {
                        this.mServiceBinderWrapper.addSubscription(mRootId, callbacks.get(i).mToken, optionsList.get(i), this.mCallbacksMessenger);
                    }
                }
            }
            catch (RemoteException ex) {
                Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
            }
        }
    }
    
    @Override
    public void subscribe(final String s, final Bundle bundle, final MediaBrowserCompat$SubscriptionCallback mediaBrowserCompat$SubscriptionCallback) {
        MediaBrowserCompat$Subscription mediaBrowserCompat$Subscription;
        if ((mediaBrowserCompat$Subscription = this.mSubscriptions.get(s)) == null) {
            mediaBrowserCompat$Subscription = new MediaBrowserCompat$Subscription();
            this.mSubscriptions.put(s, mediaBrowserCompat$Subscription);
        }
        mediaBrowserCompat$Subscription.putCallback(bundle, mediaBrowserCompat$SubscriptionCallback);
        if (this.mState != 2) {
            return;
        }
        try {
            this.mServiceBinderWrapper.addSubscription(s, mediaBrowserCompat$SubscriptionCallback.mToken, bundle, this.mCallbacksMessenger);
        }
        catch (RemoteException ex) {
            Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + s);
        }
    }
    
    @Override
    public void unsubscribe(final String s, final MediaBrowserCompat$SubscriptionCallback mediaBrowserCompat$SubscriptionCallback) {
        final MediaBrowserCompat$Subscription mediaBrowserCompat$Subscription = this.mSubscriptions.get(s);
        if (mediaBrowserCompat$Subscription != null) {
            Label_0066: {
                if (mediaBrowserCompat$SubscriptionCallback != null) {
                    break Label_0066;
                }
                while (true) {
                    try {
                        if (this.mState == 2) {
                            this.mServiceBinderWrapper.removeSubscription(s, null, this.mCallbacksMessenger);
                        }
                        if (mediaBrowserCompat$Subscription.isEmpty() || mediaBrowserCompat$SubscriptionCallback == null) {
                            this.mSubscriptions.remove(s);
                            return;
                        }
                        return;
                        final List<MediaBrowserCompat$SubscriptionCallback> callbacks = mediaBrowserCompat$Subscription.getCallbacks();
                        final List<Bundle> optionsList = mediaBrowserCompat$Subscription.getOptionsList();
                        int n = callbacks.size() - 1;
                        Block_5_Outer:Label_0130_Outer:
                        while (true) {
                            break Label_0090;
                        Label_0148:
                            while (true) {
                                Block_7: {
                                    while (true) {
                                        break Block_7;
                                        continue Label_0130_Outer;
                                    }
                                    callbacks.remove(n);
                                    optionsList.remove(n);
                                    break Label_0148;
                                }
                                this.mServiceBinderWrapper.removeSubscription(s, mediaBrowserCompat$SubscriptionCallback.mToken, this.mCallbacksMessenger);
                                continue;
                            }
                            --n;
                            continue Block_5_Outer;
                        }
                    }
                    // iftrue(Label_0148:, callbacks.get(n) != mediaBrowserCompat$SubscriptionCallback)
                    // iftrue(Label_0130:, this.mState != 2)
                    // iftrue(Label_0044:, n < 0)
                    catch (RemoteException ex) {
                        Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + s);
                        continue;
                    }
                    break;
                }
            }
        }
    }
}
