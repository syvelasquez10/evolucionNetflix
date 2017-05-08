// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.util.Iterator;
import android.os.RemoteException;
import android.util.Log;
import android.support.v4.media.session.MediaSessionCompat$Token;

class MediaBrowserServiceCompat$MediaBrowserServiceImplBase$1 implements Runnable
{
    final /* synthetic */ MediaBrowserServiceCompat$MediaBrowserServiceImplBase this$1;
    final /* synthetic */ MediaSessionCompat$Token val$token;
    
    MediaBrowserServiceCompat$MediaBrowserServiceImplBase$1(final MediaBrowserServiceCompat$MediaBrowserServiceImplBase this$1, final MediaSessionCompat$Token val$token) {
        this.this$1 = this$1;
        this.val$token = val$token;
    }
    
    @Override
    public void run() {
        final Iterator<MediaBrowserServiceCompat$ConnectionRecord> iterator = this.this$1.this$0.mConnections.values().iterator();
        while (iterator.hasNext()) {
            final MediaBrowserServiceCompat$ConnectionRecord mediaBrowserServiceCompat$ConnectionRecord = iterator.next();
            try {
                mediaBrowserServiceCompat$ConnectionRecord.callbacks.onConnect(mediaBrowserServiceCompat$ConnectionRecord.root.getRootId(), this.val$token, mediaBrowserServiceCompat$ConnectionRecord.root.getExtras());
            }
            catch (RemoteException ex) {
                Log.w("MBServiceCompat", "Connection for " + mediaBrowserServiceCompat$ConnectionRecord.pkg + " is no longer valid.");
                iterator.remove();
            }
        }
    }
}
