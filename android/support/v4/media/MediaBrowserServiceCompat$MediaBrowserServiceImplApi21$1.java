// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.util.Iterator;
import android.support.v4.util.Pair;
import java.util.List;
import android.os.IBinder;
import android.os.Bundle;

class MediaBrowserServiceCompat$MediaBrowserServiceImplApi21$1 implements Runnable
{
    final /* synthetic */ MediaBrowserServiceCompat$MediaBrowserServiceImplApi21 this$1;
    final /* synthetic */ Bundle val$options;
    final /* synthetic */ String val$parentId;
    
    MediaBrowserServiceCompat$MediaBrowserServiceImplApi21$1(final MediaBrowserServiceCompat$MediaBrowserServiceImplApi21 this$1, final String val$parentId, final Bundle val$options) {
        this.this$1 = this$1;
        this.val$parentId = val$parentId;
        this.val$options = val$options;
    }
    
    @Override
    public void run() {
        final Iterator<IBinder> iterator = this.this$1.this$0.mConnections.keySet().iterator();
        while (iterator.hasNext()) {
            final MediaBrowserServiceCompat$ConnectionRecord mediaBrowserServiceCompat$ConnectionRecord = this.this$1.this$0.mConnections.get(iterator.next());
            final List<Pair<IBinder, Bundle>> list = mediaBrowserServiceCompat$ConnectionRecord.subscriptions.get(this.val$parentId);
            if (list != null) {
                for (final Pair<IBinder, Bundle> pair : list) {
                    if (MediaBrowserCompatUtils.hasDuplicatedItems(this.val$options, pair.second)) {
                        this.this$1.this$0.performLoadChildren(this.val$parentId, mediaBrowserServiceCompat$ConnectionRecord, pair.second);
                    }
                }
            }
        }
    }
}
