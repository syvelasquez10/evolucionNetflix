// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.text.TextUtils;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.support.v4.os.BuildCompat;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

public final class MediaBrowserCompat
{
    static final boolean DEBUG;
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    static final String TAG = "MediaBrowserCompat";
    private final MediaBrowserCompat$MediaBrowserImpl mImpl;
    
    static {
        DEBUG = Log.isLoggable("MediaBrowserCompat", 3);
    }
    
    public MediaBrowserCompat(final Context context, final ComponentName componentName, final MediaBrowserCompat$ConnectionCallback mediaBrowserCompat$ConnectionCallback, final Bundle bundle) {
        if (Build$VERSION.SDK_INT >= 24 || BuildCompat.isAtLeastN()) {
            this.mImpl = new MediaBrowserCompat$MediaBrowserImplApi24(context, componentName, mediaBrowserCompat$ConnectionCallback, bundle);
            return;
        }
        if (Build$VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaBrowserCompat$MediaBrowserImplApi23(context, componentName, mediaBrowserCompat$ConnectionCallback, bundle);
            return;
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaBrowserCompat$MediaBrowserImplApi21(context, componentName, mediaBrowserCompat$ConnectionCallback, bundle);
            return;
        }
        this.mImpl = new MediaBrowserCompat$MediaBrowserImplBase(context, componentName, mediaBrowserCompat$ConnectionCallback, bundle);
    }
    
    public void connect() {
        this.mImpl.connect();
    }
    
    public void disconnect() {
        this.mImpl.disconnect();
    }
    
    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }
    
    public void getItem(final String s, final MediaBrowserCompat$ItemCallback mediaBrowserCompat$ItemCallback) {
        this.mImpl.getItem(s, mediaBrowserCompat$ItemCallback);
    }
    
    public String getRoot() {
        return this.mImpl.getRoot();
    }
    
    public ComponentName getServiceComponent() {
        return this.mImpl.getServiceComponent();
    }
    
    public MediaSessionCompat$Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }
    
    public boolean isConnected() {
        return this.mImpl.isConnected();
    }
    
    public void subscribe(final String s, final Bundle bundle, final MediaBrowserCompat$SubscriptionCallback mediaBrowserCompat$SubscriptionCallback) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (mediaBrowserCompat$SubscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        }
        if (bundle == null) {
            throw new IllegalArgumentException("options are null");
        }
        this.mImpl.subscribe(s, bundle, mediaBrowserCompat$SubscriptionCallback);
    }
    
    public void subscribe(final String s, final MediaBrowserCompat$SubscriptionCallback mediaBrowserCompat$SubscriptionCallback) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (mediaBrowserCompat$SubscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        }
        this.mImpl.subscribe(s, null, mediaBrowserCompat$SubscriptionCallback);
    }
    
    public void unsubscribe(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        this.mImpl.unsubscribe(s, null);
    }
    
    public void unsubscribe(final String s, final MediaBrowserCompat$SubscriptionCallback mediaBrowserCompat$SubscriptionCallback) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (mediaBrowserCompat$SubscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        }
        this.mImpl.unsubscribe(s, mediaBrowserCompat$SubscriptionCallback);
    }
}
