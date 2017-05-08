// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.util.Iterator;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;

class MediaBrowserServiceCompat$MediaBrowserServiceImplApi24$1 extends MediaBrowserServiceCompat$Result<List<MediaBrowserCompat$MediaItem>>
{
    final /* synthetic */ MediaBrowserServiceCompat$MediaBrowserServiceImplApi24 this$1;
    final /* synthetic */ MediaBrowserServiceCompatApi24$ResultWrapper val$resultWrapper;
    
    MediaBrowserServiceCompat$MediaBrowserServiceImplApi24$1(final MediaBrowserServiceCompat$MediaBrowserServiceImplApi24 this$1, final Object o, final MediaBrowserServiceCompatApi24$ResultWrapper val$resultWrapper) {
        this.this$1 = this$1;
        this.val$resultWrapper = val$resultWrapper;
        super(o);
    }
    
    @Override
    public void detach() {
        this.val$resultWrapper.detach();
    }
    
    @Override
    void onResultSent(final List<MediaBrowserCompat$MediaItem> list, final int n) {
        List<Parcel> list2 = null;
        if (list != null) {
            list2 = new ArrayList<Parcel>();
            for (final MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem : list) {
                final Parcel obtain = Parcel.obtain();
                mediaBrowserCompat$MediaItem.writeToParcel(obtain, 0);
                list2.add(obtain);
            }
        }
        this.val$resultWrapper.sendResult(list2, n);
    }
}
