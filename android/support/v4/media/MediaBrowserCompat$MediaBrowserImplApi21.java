// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Binder;
import android.support.v4.os.BuildCompat;
import java.lang.ref.WeakReference;
import java.util.List;
import android.os.IBinder;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.support.v4.os.ResultReceiver;
import android.os.Handler;
import android.text.TextUtils;
import android.os.RemoteException;
import android.util.Log;
import android.os.Build$VERSION;
import android.content.ComponentName;
import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.os.Bundle;
import android.os.Messenger;

class MediaBrowserCompat$MediaBrowserImplApi21 implements MediaBrowserCompat$ConnectionCallback$ConnectionCallbackInternal, MediaBrowserCompat$MediaBrowserImpl, MediaBrowserCompat$MediaBrowserServiceCallbackImpl
{
    protected final Object mBrowserObj;
    protected Messenger mCallbacksMessenger;
    protected final MediaBrowserCompat$CallbackHandler mHandler;
    protected final Bundle mRootHints;
    protected MediaBrowserCompat$ServiceBinderWrapper mServiceBinderWrapper;
    private final ArrayMap<String, MediaBrowserCompat$Subscription> mSubscriptions;
    
    public MediaBrowserCompat$MediaBrowserImplApi21(final Context context, final ComponentName componentName, final MediaBrowserCompat$ConnectionCallback mediaBrowserCompat$ConnectionCallback, Bundle mRootHints) {
        this.mHandler = new MediaBrowserCompat$CallbackHandler(this);
        this.mSubscriptions = new ArrayMap<String, MediaBrowserCompat$Subscription>();
        if (Build$VERSION.SDK_INT < 25) {
            Bundle bundle;
            if ((bundle = mRootHints) == null) {
                bundle = new Bundle();
            }
            bundle.putInt("extra_client_version", 1);
            this.mRootHints = new Bundle(bundle);
        }
        else {
            if (mRootHints == null) {
                mRootHints = null;
            }
            else {
                mRootHints = new Bundle(mRootHints);
            }
            this.mRootHints = mRootHints;
        }
        mediaBrowserCompat$ConnectionCallback.setInternalConnectionCallback(this);
        this.mBrowserObj = MediaBrowserCompatApi21.createBrowser(context, componentName, mediaBrowserCompat$ConnectionCallback.mConnectionCallbackObj, this.mRootHints);
    }
    
    @Override
    public void connect() {
        MediaBrowserCompatApi21.connect(this.mBrowserObj);
    }
    
    @Override
    public void disconnect() {
        while (true) {
            if (this.mServiceBinderWrapper == null || this.mCallbacksMessenger == null) {
                break Label_0025;
            }
            try {
                this.mServiceBinderWrapper.unregisterCallbackMessenger(this.mCallbacksMessenger);
                MediaBrowserCompatApi21.disconnect(this.mBrowserObj);
            }
            catch (RemoteException ex) {
                Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                continue;
            }
            break;
        }
    }
    
    @Override
    public Bundle getExtras() {
        return MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
    }
    
    @Override
    public void getItem(final String s, final MediaBrowserCompat$ItemCallback mediaBrowserCompat$ItemCallback) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("mediaId is empty");
        }
        if (mediaBrowserCompat$ItemCallback == null) {
            throw new IllegalArgumentException("cb is null");
        }
        if (!MediaBrowserCompatApi21.isConnected(this.mBrowserObj)) {
            Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
            this.mHandler.post((Runnable)new MediaBrowserCompat$MediaBrowserImplApi21$1(this, mediaBrowserCompat$ItemCallback, s));
            return;
        }
        if (this.mServiceBinderWrapper == null) {
            this.mHandler.post((Runnable)new MediaBrowserCompat$MediaBrowserImplApi21$2(this, mediaBrowserCompat$ItemCallback, s));
            return;
        }
        final MediaBrowserCompat$ItemReceiver mediaBrowserCompat$ItemReceiver = new MediaBrowserCompat$ItemReceiver(s, mediaBrowserCompat$ItemCallback, this.mHandler);
        try {
            this.mServiceBinderWrapper.getMediaItem(s, mediaBrowserCompat$ItemReceiver, this.mCallbacksMessenger);
        }
        catch (RemoteException ex) {
            Log.i("MediaBrowserCompat", "Remote error getting media item: " + s);
            this.mHandler.post((Runnable)new MediaBrowserCompat$MediaBrowserImplApi21$3(this, mediaBrowserCompat$ItemCallback, s));
        }
    }
    
    @Override
    public String getRoot() {
        return MediaBrowserCompatApi21.getRoot(this.mBrowserObj);
    }
    
    @Override
    public ComponentName getServiceComponent() {
        return MediaBrowserCompatApi21.getServiceComponent(this.mBrowserObj);
    }
    
    @Override
    public MediaSessionCompat$Token getSessionToken() {
        return MediaSessionCompat$Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj));
    }
    
    @Override
    public boolean isConnected() {
        return MediaBrowserCompatApi21.isConnected(this.mBrowserObj);
    }
    
    @Override
    public void onConnected() {
        final Bundle extras = MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
        if (extras != null) {
            final IBinder binder = BundleCompat.getBinder(extras, "extra_messenger");
            if (binder != null) {
                this.mServiceBinderWrapper = new MediaBrowserCompat$ServiceBinderWrapper(binder, this.mRootHints);
                this.mCallbacksMessenger = new Messenger((Handler)this.mHandler);
                this.mHandler.setCallbacksMessenger(this.mCallbacksMessenger);
                try {
                    this.mServiceBinderWrapper.registerCallbackMessenger(this.mCallbacksMessenger);
                }
                catch (RemoteException ex) {
                    Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                }
            }
        }
    }
    
    @Override
    public void onConnectionFailed() {
    }
    
    @Override
    public void onConnectionFailed(final Messenger messenger) {
    }
    
    @Override
    public void onConnectionSuspended() {
        this.mServiceBinderWrapper = null;
        this.mCallbacksMessenger = null;
        this.mHandler.setCallbacksMessenger(null);
    }
    
    @Override
    public void onLoadChildren(final Messenger messenger, final String s, final List list, final Bundle bundle) {
        if (this.mCallbacksMessenger == messenger) {
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
    public void onServiceConnected(final Messenger messenger, final String s, final MediaSessionCompat$Token mediaSessionCompat$Token, final Bundle bundle) {
    }
    
    @Override
    public void subscribe(final String s, final Bundle bundle, final MediaBrowserCompat$SubscriptionCallback mediaBrowserCompat$SubscriptionCallback) {
        MediaBrowserCompat$Subscription mediaBrowserCompat$Subscription;
        if ((mediaBrowserCompat$Subscription = this.mSubscriptions.get(s)) == null) {
            mediaBrowserCompat$Subscription = new MediaBrowserCompat$Subscription();
            this.mSubscriptions.put(s, mediaBrowserCompat$Subscription);
        }
        mediaBrowserCompat$SubscriptionCallback.setSubscription(mediaBrowserCompat$Subscription);
        mediaBrowserCompat$Subscription.putCallback(bundle, mediaBrowserCompat$SubscriptionCallback);
        if (this.mServiceBinderWrapper == null) {
            MediaBrowserCompatApi21.subscribe(this.mBrowserObj, s, mediaBrowserCompat$SubscriptionCallback.mSubscriptionCallbackObj);
            return;
        }
        try {
            this.mServiceBinderWrapper.addSubscription(s, mediaBrowserCompat$SubscriptionCallback.mToken, bundle, this.mCallbacksMessenger);
        }
        catch (RemoteException ex) {
            Log.i("MediaBrowserCompat", "Remote error subscribing media item: " + s);
        }
    }
    
    @Override
    public void unsubscribe(final String s, final MediaBrowserCompat$SubscriptionCallback mediaBrowserCompat$SubscriptionCallback) {
        final MediaBrowserCompat$Subscription mediaBrowserCompat$Subscription = this.mSubscriptions.get(s);
        if (mediaBrowserCompat$Subscription != null) {
            if (this.mServiceBinderWrapper == null) {
                if (mediaBrowserCompat$SubscriptionCallback == null) {
                    MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, s);
                }
                else {
                    final List<MediaBrowserCompat$SubscriptionCallback> callbacks = mediaBrowserCompat$Subscription.getCallbacks();
                    final List<Bundle> optionsList = mediaBrowserCompat$Subscription.getOptionsList();
                    for (int i = callbacks.size() - 1; i >= 0; --i) {
                        if (callbacks.get(i) == mediaBrowserCompat$SubscriptionCallback) {
                            callbacks.remove(i);
                            optionsList.remove(i);
                        }
                    }
                    if (callbacks.size() == 0) {
                        MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, s);
                    }
                }
            }
            else if (mediaBrowserCompat$SubscriptionCallback == null) {
                try {
                    this.mServiceBinderWrapper.removeSubscription(s, null, this.mCallbacksMessenger);
                }
                catch (RemoteException ex) {
                    Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + s);
                }
            }
            else {
                final List<MediaBrowserCompat$SubscriptionCallback> callbacks2 = mediaBrowserCompat$Subscription.getCallbacks();
                final List<Bundle> optionsList2 = mediaBrowserCompat$Subscription.getOptionsList();
                for (int j = callbacks2.size() - 1; j >= 0; --j) {
                    if (callbacks2.get(j) == mediaBrowserCompat$SubscriptionCallback) {
                        this.mServiceBinderWrapper.removeSubscription(s, mediaBrowserCompat$SubscriptionCallback.mToken, this.mCallbacksMessenger);
                        callbacks2.remove(j);
                        optionsList2.remove(j);
                    }
                }
            }
            if (mediaBrowserCompat$Subscription.isEmpty() || mediaBrowserCompat$SubscriptionCallback == null) {
                this.mSubscriptions.remove(s);
            }
        }
    }
}
