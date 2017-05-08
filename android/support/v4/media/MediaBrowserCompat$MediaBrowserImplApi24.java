// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.util.List;
import android.os.Binder;
import android.support.v4.os.BuildCompat;
import android.os.Build$VERSION;
import android.os.IBinder;
import java.lang.ref.WeakReference;
import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;

class MediaBrowserCompat$MediaBrowserImplApi24 extends MediaBrowserCompat$MediaBrowserImplApi23
{
    public MediaBrowserCompat$MediaBrowserImplApi24(final Context context, final ComponentName componentName, final MediaBrowserCompat$ConnectionCallback mediaBrowserCompat$ConnectionCallback, final Bundle bundle) {
        super(context, componentName, mediaBrowserCompat$ConnectionCallback, bundle);
    }
    
    @Override
    public void subscribe(final String s, final Bundle bundle, final MediaBrowserCompat$SubscriptionCallback mediaBrowserCompat$SubscriptionCallback) {
        if (bundle == null) {
            MediaBrowserCompatApi21.subscribe(this.mBrowserObj, s, mediaBrowserCompat$SubscriptionCallback.mSubscriptionCallbackObj);
            return;
        }
        MediaBrowserCompatApi24.subscribe(this.mBrowserObj, s, bundle, mediaBrowserCompat$SubscriptionCallback.mSubscriptionCallbackObj);
    }
    
    @Override
    public void unsubscribe(final String s, final MediaBrowserCompat$SubscriptionCallback mediaBrowserCompat$SubscriptionCallback) {
        if (mediaBrowserCompat$SubscriptionCallback == null) {
            MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, s);
            return;
        }
        MediaBrowserCompatApi24.unsubscribe(this.mBrowserObj, s, mediaBrowserCompat$SubscriptionCallback.mSubscriptionCallbackObj);
    }
}
