// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.util.Iterator;
import java.util.ArrayList;
import android.media.browse.MediaBrowser$MediaItem;
import android.os.Parcel;
import java.util.List;
import android.service.media.MediaBrowserService$Result;

class MediaBrowserServiceCompatApi21$ResultWrapper<T>
{
    MediaBrowserService$Result mResultObj;
    
    MediaBrowserServiceCompatApi21$ResultWrapper(final MediaBrowserService$Result mResultObj) {
        this.mResultObj = mResultObj;
    }
    
    public void detach() {
        this.mResultObj.detach();
    }
    
    List<MediaBrowser$MediaItem> parcelListToItemList(final List<Parcel> list) {
        if (list == null) {
            return null;
        }
        final ArrayList<Object> list2 = (ArrayList<Object>)new ArrayList<MediaBrowser$MediaItem>();
        for (final Parcel parcel : list) {
            parcel.setDataPosition(0);
            list2.add(MediaBrowser$MediaItem.CREATOR.createFromParcel(parcel));
            parcel.recycle();
        }
        return (List<MediaBrowser$MediaItem>)list2;
    }
    
    public void sendResult(final T t) {
        if (t instanceof List) {
            this.mResultObj.sendResult((Object)this.parcelListToItemList((List<Parcel>)t));
            return;
        }
        if (t instanceof Parcel) {
            final Parcel parcel = (Parcel)t;
            parcel.setDataPosition(0);
            this.mResultObj.sendResult(MediaBrowser$MediaItem.CREATOR.createFromParcel(parcel));
            parcel.recycle();
            return;
        }
        this.mResultObj.sendResult((Object)null);
    }
}
