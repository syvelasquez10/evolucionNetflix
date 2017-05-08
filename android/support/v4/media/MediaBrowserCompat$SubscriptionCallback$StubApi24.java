// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Bundle;
import java.util.List;

class MediaBrowserCompat$SubscriptionCallback$StubApi24 extends MediaBrowserCompat$SubscriptionCallback$StubApi21 implements MediaBrowserCompatApi24$SubscriptionCallback
{
    final /* synthetic */ MediaBrowserCompat$SubscriptionCallback this$0;
    
    MediaBrowserCompat$SubscriptionCallback$StubApi24(final MediaBrowserCompat$SubscriptionCallback this$0) {
        this.this$0 = this$0;
        super(this$0);
    }
    
    @Override
    public void onChildrenLoaded(final String s, final List<?> list, final Bundle bundle) {
        this.this$0.onChildrenLoaded(s, MediaBrowserCompat$MediaItem.fromMediaItemList(list), bundle);
    }
    
    @Override
    public void onError(final String s, final Bundle bundle) {
        this.this$0.onError(s, bundle);
    }
}
