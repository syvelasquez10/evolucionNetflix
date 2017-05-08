// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.text.TextUtils;
import android.support.v4.os.ResultReceiver;
import android.os.Bundle;
import android.os.IBinder;

class MediaBrowserServiceCompat$ServiceBinderImpl
{
    final /* synthetic */ MediaBrowserServiceCompat this$0;
    
    MediaBrowserServiceCompat$ServiceBinderImpl(final MediaBrowserServiceCompat this$0) {
        this.this$0 = this$0;
    }
    
    public void addSubscription(final String s, final IBinder binder, final Bundle bundle, final MediaBrowserServiceCompat$ServiceCallbacks mediaBrowserServiceCompat$ServiceCallbacks) {
        this.this$0.mHandler.postOrRun(new MediaBrowserServiceCompat$ServiceBinderImpl$3(this, mediaBrowserServiceCompat$ServiceCallbacks, s, binder, bundle));
    }
    
    public void connect(final String s, final int n, final Bundle bundle, final MediaBrowserServiceCompat$ServiceCallbacks mediaBrowserServiceCompat$ServiceCallbacks) {
        if (!this.this$0.isValidPackage(s, n)) {
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + n + " package=" + s);
        }
        this.this$0.mHandler.postOrRun(new MediaBrowserServiceCompat$ServiceBinderImpl$1(this, mediaBrowserServiceCompat$ServiceCallbacks, s, bundle, n));
    }
    
    public void disconnect(final MediaBrowserServiceCompat$ServiceCallbacks mediaBrowserServiceCompat$ServiceCallbacks) {
        this.this$0.mHandler.postOrRun(new MediaBrowserServiceCompat$ServiceBinderImpl$2(this, mediaBrowserServiceCompat$ServiceCallbacks));
    }
    
    public void getMediaItem(final String s, final ResultReceiver resultReceiver, final MediaBrowserServiceCompat$ServiceCallbacks mediaBrowserServiceCompat$ServiceCallbacks) {
        if (TextUtils.isEmpty((CharSequence)s) || resultReceiver == null) {
            return;
        }
        this.this$0.mHandler.postOrRun(new MediaBrowserServiceCompat$ServiceBinderImpl$5(this, mediaBrowserServiceCompat$ServiceCallbacks, s, resultReceiver));
    }
    
    public void registerCallbacks(final MediaBrowserServiceCompat$ServiceCallbacks mediaBrowserServiceCompat$ServiceCallbacks, final Bundle bundle) {
        this.this$0.mHandler.postOrRun(new MediaBrowserServiceCompat$ServiceBinderImpl$6(this, mediaBrowserServiceCompat$ServiceCallbacks, bundle));
    }
    
    public void removeSubscription(final String s, final IBinder binder, final MediaBrowserServiceCompat$ServiceCallbacks mediaBrowserServiceCompat$ServiceCallbacks) {
        this.this$0.mHandler.postOrRun(new MediaBrowserServiceCompat$ServiceBinderImpl$4(this, mediaBrowserServiceCompat$ServiceCallbacks, s, binder));
    }
    
    public void unregisterCallbacks(final MediaBrowserServiceCompat$ServiceCallbacks mediaBrowserServiceCompat$ServiceCallbacks) {
        this.this$0.mHandler.postOrRun(new MediaBrowserServiceCompat$ServiceBinderImpl$7(this, mediaBrowserServiceCompat$ServiceCallbacks));
    }
}
