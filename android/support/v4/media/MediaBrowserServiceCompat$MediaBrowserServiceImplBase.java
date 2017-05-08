// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.support.v4.media.session.MediaSessionCompat$Token;
import android.os.Handler;
import android.os.IBinder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Messenger;

class MediaBrowserServiceCompat$MediaBrowserServiceImplBase implements MediaBrowserServiceCompat$MediaBrowserServiceImpl
{
    private Messenger mMessenger;
    final /* synthetic */ MediaBrowserServiceCompat this$0;
    
    MediaBrowserServiceCompat$MediaBrowserServiceImplBase(final MediaBrowserServiceCompat this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Bundle getBrowserRootHints() {
        if (this.this$0.mCurConnection == null) {
            throw new IllegalStateException("This should be called inside of onLoadChildren or onLoadItem methods");
        }
        if (this.this$0.mCurConnection.rootHints == null) {
            return null;
        }
        return new Bundle(this.this$0.mCurConnection.rootHints);
    }
    
    @Override
    public void notifyChildrenChanged(final String s, final Bundle bundle) {
        this.this$0.mHandler.post((Runnable)new MediaBrowserServiceCompat$MediaBrowserServiceImplBase$2(this, s, bundle));
    }
    
    @Override
    public IBinder onBind(final Intent intent) {
        if ("android.media.browse.MediaBrowserService".equals(intent.getAction())) {
            return this.mMessenger.getBinder();
        }
        return null;
    }
    
    @Override
    public void onCreate() {
        this.mMessenger = new Messenger((Handler)this.this$0.mHandler);
    }
    
    @Override
    public void setSessionToken(final MediaSessionCompat$Token mediaSessionCompat$Token) {
        this.this$0.mHandler.post((Runnable)new MediaBrowserServiceCompat$MediaBrowserServiceImplBase$1(this, mediaSessionCompat$Token));
    }
}
