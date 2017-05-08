// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.RemoteException;
import android.os.Handler;
import android.os.Messenger;
import android.util.Log;
import android.content.ComponentName;
import android.os.IBinder;

class MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1 implements Runnable
{
    final /* synthetic */ MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection this$1;
    final /* synthetic */ IBinder val$binder;
    final /* synthetic */ ComponentName val$name;
    
    MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1(final MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection this$1, final ComponentName val$name, final IBinder val$binder) {
        this.this$1 = this$1;
        this.val$name = val$name;
        this.val$binder = val$binder;
    }
    
    @Override
    public void run() {
        if (MediaBrowserCompat.DEBUG) {
            Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + this.val$name + " binder=" + this.val$binder);
            this.this$1.this$0.dump();
        }
        if (this.this$1.isCurrent("onServiceConnected")) {
            this.this$1.this$0.mServiceBinderWrapper = new MediaBrowserCompat$ServiceBinderWrapper(this.val$binder, this.this$1.this$0.mRootHints);
            this.this$1.this$0.mCallbacksMessenger = new Messenger((Handler)this.this$1.this$0.mHandler);
            this.this$1.this$0.mHandler.setCallbacksMessenger(this.this$1.this$0.mCallbacksMessenger);
            this.this$1.this$0.mState = 1;
            try {
                if (MediaBrowserCompat.DEBUG) {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    this.this$1.this$0.dump();
                }
                this.this$1.this$0.mServiceBinderWrapper.connect(this.this$1.this$0.mContext, this.this$1.this$0.mCallbacksMessenger);
            }
            catch (RemoteException ex) {
                Log.w("MediaBrowserCompat", "RemoteException during connect for " + this.this$1.this$0.mServiceComponent);
                if (MediaBrowserCompat.DEBUG) {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    this.this$1.this$0.dump();
                }
            }
        }
    }
}
