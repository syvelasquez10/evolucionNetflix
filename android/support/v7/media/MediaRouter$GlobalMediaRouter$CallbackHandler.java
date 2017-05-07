// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.List;
import java.util.Collections;
import android.util.Log;
import java.util.Iterator;
import java.util.Locale;
import android.support.v4.app.ActivityManagerCompat;
import android.app.ActivityManager;
import android.support.v4.media.session.MediaSessionCompat$OnActiveChangeListener;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.content.Context;
import java.util.Collection;
import java.lang.ref.WeakReference;
import android.os.Message;
import java.util.ArrayList;
import android.os.Handler;

final class MediaRouter$GlobalMediaRouter$CallbackHandler extends Handler
{
    private final ArrayList<MediaRouter$CallbackRecord> mTempCallbackRecords;
    final /* synthetic */ MediaRouter$GlobalMediaRouter this$0;
    
    private MediaRouter$GlobalMediaRouter$CallbackHandler(final MediaRouter$GlobalMediaRouter this$0) {
        this.this$0 = this$0;
        this.mTempCallbackRecords = new ArrayList<MediaRouter$CallbackRecord>();
    }
    
    private void invokeCallback(final MediaRouter$CallbackRecord mediaRouter$CallbackRecord, final int n, final Object o) {
        final MediaRouter mRouter = mediaRouter$CallbackRecord.mRouter;
        final MediaRouter$Callback mCallback = mediaRouter$CallbackRecord.mCallback;
        switch (0xFF00 & n) {
            case 256: {
                final MediaRouter$RouteInfo mediaRouter$RouteInfo = (MediaRouter$RouteInfo)o;
                if (!mediaRouter$CallbackRecord.filterRouteEvent(mediaRouter$RouteInfo)) {
                    break;
                }
                switch (n) {
                    default: {
                        return;
                    }
                    case 257: {
                        mCallback.onRouteAdded(mRouter, mediaRouter$RouteInfo);
                        return;
                    }
                    case 258: {
                        mCallback.onRouteRemoved(mRouter, mediaRouter$RouteInfo);
                        return;
                    }
                    case 259: {
                        mCallback.onRouteChanged(mRouter, mediaRouter$RouteInfo);
                        return;
                    }
                    case 260: {
                        mCallback.onRouteVolumeChanged(mRouter, mediaRouter$RouteInfo);
                        return;
                    }
                    case 261: {
                        mCallback.onRoutePresentationDisplayChanged(mRouter, mediaRouter$RouteInfo);
                        return;
                    }
                    case 262: {
                        mCallback.onRouteSelected(mRouter, mediaRouter$RouteInfo);
                        return;
                    }
                    case 263: {
                        mCallback.onRouteUnselected(mRouter, mediaRouter$RouteInfo);
                        return;
                    }
                }
                break;
            }
            case 512: {
                final MediaRouter$ProviderInfo mediaRouter$ProviderInfo = (MediaRouter$ProviderInfo)o;
                switch (n) {
                    default: {
                        return;
                    }
                    case 513: {
                        mCallback.onProviderAdded(mRouter, mediaRouter$ProviderInfo);
                        return;
                    }
                    case 514: {
                        mCallback.onProviderRemoved(mRouter, mediaRouter$ProviderInfo);
                        return;
                    }
                    case 515: {
                        mCallback.onProviderChanged(mRouter, mediaRouter$ProviderInfo);
                        return;
                    }
                }
                break;
            }
        }
    }
    
    private void syncWithSystemProvider(final int n, final Object o) {
        switch (n) {
            default: {}
            case 257: {
                this.this$0.mSystemProvider.onSyncRouteAdded((MediaRouter$RouteInfo)o);
            }
            case 258: {
                this.this$0.mSystemProvider.onSyncRouteRemoved((MediaRouter$RouteInfo)o);
            }
            case 259: {
                this.this$0.mSystemProvider.onSyncRouteChanged((MediaRouter$RouteInfo)o);
            }
            case 262: {
                this.this$0.mSystemProvider.onSyncRouteSelected((MediaRouter$RouteInfo)o);
            }
        }
    }
    
    public void handleMessage(final Message message) {
        int what;
        while (true) {
            what = message.what;
            this.syncWithSystemProvider(what, message.obj);
            while (true) {
                Label_0152: {
                    try {
                        int size = this.this$0.mRouters.size();
                        --size;
                        if (size >= 0) {
                            final MediaRouter mediaRouter = this.this$0.mRouters.get(size).get();
                            if (mediaRouter == null) {
                                this.this$0.mRouters.remove(size);
                                break Label_0152;
                            }
                            this.mTempCallbackRecords.addAll(mediaRouter.mCallbackRecords);
                            break Label_0152;
                        }
                    }
                    finally {
                        this.mTempCallbackRecords.clear();
                    }
                    break;
                }
                continue;
            }
        }
        for (int size2 = this.mTempCallbackRecords.size(), i = 0; i < size2; ++i) {
            final Throwable t;
            this.invokeCallback(this.mTempCallbackRecords.get(i), what, t);
        }
        this.mTempCallbackRecords.clear();
    }
    
    public void post(final int n, final Object o) {
        this.obtainMessage(n, o).sendToTarget();
    }
}
