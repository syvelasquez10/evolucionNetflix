// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.IBinder;
import android.content.ComponentName;
import android.util.Log;
import android.content.ServiceConnection;

class MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection implements ServiceConnection
{
    final /* synthetic */ MediaBrowserCompat$MediaBrowserImplBase this$0;
    
    MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection(final MediaBrowserCompat$MediaBrowserImplBase this$0) {
        this.this$0 = this$0;
    }
    
    private void postOrRun(final Runnable runnable) {
        if (Thread.currentThread() == this.this$0.mHandler.getLooper().getThread()) {
            runnable.run();
            return;
        }
        this.this$0.mHandler.post(runnable);
    }
    
    boolean isCurrent(final String s) {
        if (this.this$0.mServiceConnection != this) {
            if (this.this$0.mState != 0) {
                Log.i("MediaBrowserCompat", s + " for " + this.this$0.mServiceComponent + " with mServiceConnection=" + this.this$0.mServiceConnection + " this=" + this);
            }
            return false;
        }
        return true;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.postOrRun(new MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1(this, componentName, binder));
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        this.postOrRun(new MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$2(this, componentName));
    }
}
