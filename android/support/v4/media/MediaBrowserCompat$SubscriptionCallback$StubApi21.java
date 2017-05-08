// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.util.Collections;
import android.os.Bundle;
import java.util.List;

class MediaBrowserCompat$SubscriptionCallback$StubApi21 implements MediaBrowserCompatApi21$SubscriptionCallback
{
    final /* synthetic */ MediaBrowserCompat$SubscriptionCallback this$0;
    
    MediaBrowserCompat$SubscriptionCallback$StubApi21(final MediaBrowserCompat$SubscriptionCallback this$0) {
        this.this$0 = this$0;
    }
    
    List<MediaBrowserCompat$MediaItem> applyOptions(final List<MediaBrowserCompat$MediaItem> list, final Bundle bundle) {
        List<MediaBrowserCompat$MediaItem> list2;
        if (list == null) {
            list2 = null;
        }
        else {
            final int int1 = bundle.getInt("android.media.browse.extra.PAGE", -1);
            final int int2 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
            if (int1 == -1) {
                list2 = list;
                if (int2 == -1) {
                    return list2;
                }
            }
            final int n = int2 * int1;
            final int n2 = n + int2;
            if (int1 < 0 || int2 < 1 || n >= list.size()) {
                return (List<MediaBrowserCompat$MediaItem>)Collections.EMPTY_LIST;
            }
            int size;
            if ((size = n2) > list.size()) {
                size = list.size();
            }
            return list.subList(n, size);
        }
        return list2;
    }
    
    @Override
    public void onChildrenLoaded(final String s, final List<?> list) {
        MediaBrowserCompat$Subscription mediaBrowserCompat$Subscription;
        if (this.this$0.mSubscriptionRef == null) {
            mediaBrowserCompat$Subscription = null;
        }
        else {
            mediaBrowserCompat$Subscription = this.this$0.mSubscriptionRef.get();
        }
        if (mediaBrowserCompat$Subscription == null) {
            this.this$0.onChildrenLoaded(s, MediaBrowserCompat$MediaItem.fromMediaItemList(list));
        }
        else {
            final List<MediaBrowserCompat$MediaItem> fromMediaItemList = MediaBrowserCompat$MediaItem.fromMediaItemList(list);
            final List<MediaBrowserCompat$SubscriptionCallback> callbacks = mediaBrowserCompat$Subscription.getCallbacks();
            final List<Bundle> optionsList = mediaBrowserCompat$Subscription.getOptionsList();
            for (int i = 0; i < callbacks.size(); ++i) {
                final Bundle bundle = optionsList.get(i);
                if (bundle == null) {
                    this.this$0.onChildrenLoaded(s, fromMediaItemList);
                }
                else {
                    this.this$0.onChildrenLoaded(s, this.applyOptions(fromMediaItemList, bundle), bundle);
                }
            }
        }
    }
    
    @Override
    public void onError(final String s) {
        this.this$0.onError(s);
    }
}
