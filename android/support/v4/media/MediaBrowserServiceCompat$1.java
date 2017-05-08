// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.RemoteException;
import android.util.Log;
import android.os.Bundle;
import java.util.List;

class MediaBrowserServiceCompat$1 extends MediaBrowserServiceCompat$Result<List<MediaBrowserCompat$MediaItem>>
{
    final /* synthetic */ MediaBrowserServiceCompat this$0;
    final /* synthetic */ MediaBrowserServiceCompat$ConnectionRecord val$connection;
    final /* synthetic */ Bundle val$options;
    final /* synthetic */ String val$parentId;
    
    MediaBrowserServiceCompat$1(final MediaBrowserServiceCompat this$0, final Object o, final MediaBrowserServiceCompat$ConnectionRecord val$connection, final String val$parentId, final Bundle val$options) {
        this.this$0 = this$0;
        this.val$connection = val$connection;
        this.val$parentId = val$parentId;
        this.val$options = val$options;
        super(o);
    }
    
    @Override
    void onResultSent(final List<MediaBrowserCompat$MediaItem> list, final int n) {
        if (this.this$0.mConnections.get(this.val$connection.callbacks.asBinder()) != this.val$connection) {
            if (MediaBrowserServiceCompat.DEBUG) {
                Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + this.val$connection.pkg + " id=" + this.val$parentId);
            }
            return;
        }
        List<MediaBrowserCompat$MediaItem> applyOptions = list;
        if ((n & 0x1) != 0x0) {
            applyOptions = this.this$0.applyOptions(list, this.val$options);
        }
        try {
            this.val$connection.callbacks.onLoadChildren(this.val$parentId, applyOptions, this.val$options);
        }
        catch (RemoteException ex) {
            Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + this.val$parentId + " package=" + this.val$connection.pkg);
        }
    }
}
