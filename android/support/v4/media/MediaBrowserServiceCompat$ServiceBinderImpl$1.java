// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.os.Bundle;

class MediaBrowserServiceCompat$ServiceBinderImpl$1 implements Runnable
{
    final /* synthetic */ MediaBrowserServiceCompat$ServiceBinderImpl this$1;
    final /* synthetic */ MediaBrowserServiceCompat$ServiceCallbacks val$callbacks;
    final /* synthetic */ String val$pkg;
    final /* synthetic */ Bundle val$rootHints;
    final /* synthetic */ int val$uid;
    
    MediaBrowserServiceCompat$ServiceBinderImpl$1(final MediaBrowserServiceCompat$ServiceBinderImpl this$1, final MediaBrowserServiceCompat$ServiceCallbacks val$callbacks, final String val$pkg, final Bundle val$rootHints, final int val$uid) {
        this.this$1 = this$1;
        this.val$callbacks = val$callbacks;
        this.val$pkg = val$pkg;
        this.val$rootHints = val$rootHints;
        this.val$uid = val$uid;
    }
    
    @Override
    public void run() {
        final IBinder binder = this.val$callbacks.asBinder();
        this.this$1.this$0.mConnections.remove(binder);
        final MediaBrowserServiceCompat$ConnectionRecord mediaBrowserServiceCompat$ConnectionRecord = new MediaBrowserServiceCompat$ConnectionRecord(this.this$1.this$0);
        mediaBrowserServiceCompat$ConnectionRecord.pkg = this.val$pkg;
        mediaBrowserServiceCompat$ConnectionRecord.rootHints = this.val$rootHints;
        mediaBrowserServiceCompat$ConnectionRecord.callbacks = this.val$callbacks;
        mediaBrowserServiceCompat$ConnectionRecord.root = this.this$1.this$0.onGetRoot(this.val$pkg, this.val$uid, this.val$rootHints);
        Label_0180: {
            if (mediaBrowserServiceCompat$ConnectionRecord.root != null) {
                break Label_0180;
            }
            Log.i("MBServiceCompat", "No root for client " + this.val$pkg + " from service " + this.getClass().getName());
            try {
                this.val$callbacks.onConnectFailed();
                return;
            }
            catch (RemoteException ex) {
                Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + this.val$pkg);
                return;
            }
            try {
                this.this$1.this$0.mConnections.put(binder, mediaBrowserServiceCompat$ConnectionRecord);
                if (this.this$1.this$0.mSession != null) {
                    this.val$callbacks.onConnect(mediaBrowserServiceCompat$ConnectionRecord.root.getRootId(), this.this$1.this$0.mSession, mediaBrowserServiceCompat$ConnectionRecord.root.getExtras());
                }
            }
            catch (RemoteException ex2) {
                Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + this.val$pkg);
                this.this$1.this$0.mConnections.remove(binder);
            }
        }
    }
}
