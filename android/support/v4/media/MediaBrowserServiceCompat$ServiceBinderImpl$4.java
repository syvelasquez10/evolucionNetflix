// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.util.Log;
import android.os.IBinder;

class MediaBrowserServiceCompat$ServiceBinderImpl$4 implements Runnable
{
    final /* synthetic */ MediaBrowserServiceCompat$ServiceBinderImpl this$1;
    final /* synthetic */ MediaBrowserServiceCompat$ServiceCallbacks val$callbacks;
    final /* synthetic */ String val$id;
    final /* synthetic */ IBinder val$token;
    
    MediaBrowserServiceCompat$ServiceBinderImpl$4(final MediaBrowserServiceCompat$ServiceBinderImpl this$1, final MediaBrowserServiceCompat$ServiceCallbacks val$callbacks, final String val$id, final IBinder val$token) {
        this.this$1 = this$1;
        this.val$callbacks = val$callbacks;
        this.val$id = val$id;
        this.val$token = val$token;
    }
    
    @Override
    public void run() {
        final MediaBrowserServiceCompat$ConnectionRecord mediaBrowserServiceCompat$ConnectionRecord = this.this$1.this$0.mConnections.get(this.val$callbacks.asBinder());
        if (mediaBrowserServiceCompat$ConnectionRecord == null) {
            Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + this.val$id);
        }
        else if (!this.this$1.this$0.removeSubscription(this.val$id, mediaBrowserServiceCompat$ConnectionRecord, this.val$token)) {
            Log.w("MBServiceCompat", "removeSubscription called for " + this.val$id + " which is not subscribed");
        }
    }
}
