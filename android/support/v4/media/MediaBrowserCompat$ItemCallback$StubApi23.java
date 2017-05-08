// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Parcel;

class MediaBrowserCompat$ItemCallback$StubApi23 implements MediaBrowserCompatApi23$ItemCallback
{
    final /* synthetic */ MediaBrowserCompat$ItemCallback this$0;
    
    MediaBrowserCompat$ItemCallback$StubApi23(final MediaBrowserCompat$ItemCallback this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onError(final String s) {
        this.this$0.onError(s);
    }
    
    @Override
    public void onItemLoaded(final Parcel parcel) {
        parcel.setDataPosition(0);
        final MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem = (MediaBrowserCompat$MediaItem)MediaBrowserCompat$MediaItem.CREATOR.createFromParcel(parcel);
        parcel.recycle();
        this.this$0.onItemLoaded(mediaBrowserCompat$MediaItem);
    }
}
