// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.support.v4.media.session.MediaSessionCompat$Token;
import android.os.Parcel;
import java.util.List;
import android.support.v4.app.BundleCompat;
import android.os.Handler;
import android.content.Context;
import android.os.IBinder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Messenger;

class MediaBrowserServiceCompat$MediaBrowserServiceImplApi21 implements MediaBrowserServiceCompat$MediaBrowserServiceImpl, MediaBrowserServiceCompatApi21$ServiceCompatProxy
{
    Messenger mMessenger;
    Object mServiceObj;
    final /* synthetic */ MediaBrowserServiceCompat this$0;
    
    MediaBrowserServiceCompat$MediaBrowserServiceImplApi21(final MediaBrowserServiceCompat this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Bundle getBrowserRootHints() {
        if (this.mMessenger != null) {
            if (this.this$0.mCurConnection == null) {
                throw new IllegalStateException("This should be called inside of onLoadChildren or onLoadItem methods");
            }
            if (this.this$0.mCurConnection.rootHints != null) {
                return new Bundle(this.this$0.mCurConnection.rootHints);
            }
        }
        return null;
    }
    
    @Override
    public void notifyChildrenChanged(final String s, final Bundle bundle) {
        if (this.mMessenger == null) {
            MediaBrowserServiceCompatApi21.notifyChildrenChanged(this.mServiceObj, s);
            return;
        }
        this.this$0.mHandler.post((Runnable)new MediaBrowserServiceCompat$MediaBrowserServiceImplApi21$1(this, s, bundle));
    }
    
    @Override
    public IBinder onBind(final Intent intent) {
        return MediaBrowserServiceCompatApi21.onBind(this.mServiceObj, intent);
    }
    
    @Override
    public void onCreate() {
        MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj = MediaBrowserServiceCompatApi21.createService((Context)this.this$0, this));
    }
    
    @Override
    public MediaBrowserServiceCompatApi21$BrowserRoot onGetRoot(final String s, final int n, final Bundle bundle) {
        Bundle bundle2;
        if (bundle != null && bundle.getInt("extra_client_version", 0) != 0) {
            bundle.remove("extra_client_version");
            this.mMessenger = new Messenger((Handler)this.this$0.mHandler);
            bundle2 = new Bundle();
            bundle2.putInt("extra_service_version", 1);
            BundleCompat.putBinder(bundle2, "extra_messenger", this.mMessenger.getBinder());
        }
        else {
            bundle2 = null;
        }
        final MediaBrowserServiceCompat$BrowserRoot onGetRoot = this.this$0.onGetRoot(s, n, bundle);
        if (onGetRoot == null) {
            return null;
        }
        Bundle extras;
        if (bundle2 == null) {
            extras = onGetRoot.getExtras();
        }
        else {
            extras = bundle2;
            if (onGetRoot.getExtras() != null) {
                bundle2.putAll(onGetRoot.getExtras());
                extras = bundle2;
            }
        }
        return new MediaBrowserServiceCompatApi21$BrowserRoot(onGetRoot.getRootId(), extras);
    }
    
    @Override
    public void onLoadChildren(final String s, final MediaBrowserServiceCompatApi21$ResultWrapper<List<Parcel>> mediaBrowserServiceCompatApi21$ResultWrapper) {
        this.this$0.onLoadChildren(s, new MediaBrowserServiceCompat$MediaBrowserServiceImplApi21$2(this, s, mediaBrowserServiceCompatApi21$ResultWrapper));
    }
    
    @Override
    public void setSessionToken(final MediaSessionCompat$Token mediaSessionCompat$Token) {
        MediaBrowserServiceCompatApi21.setSessionToken(this.mServiceObj, mediaSessionCompat$Token.getToken());
    }
}
