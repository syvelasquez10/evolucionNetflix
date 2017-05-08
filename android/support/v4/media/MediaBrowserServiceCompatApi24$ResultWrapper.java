// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.service.media.MediaBrowserService;
import android.os.Bundle;
import android.content.Context;
import java.lang.reflect.Field;
import android.annotation.TargetApi;
import android.util.Log;
import java.util.Iterator;
import java.util.ArrayList;
import android.media.browse.MediaBrowser$MediaItem;
import android.os.Parcel;
import java.util.List;
import android.service.media.MediaBrowserService$Result;

class MediaBrowserServiceCompatApi24$ResultWrapper
{
    MediaBrowserService$Result mResultObj;
    
    MediaBrowserServiceCompatApi24$ResultWrapper(final MediaBrowserService$Result mResultObj) {
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
    
    public void sendResult(final List<Parcel> list, final int n) {
        while (true) {
            try {
                MediaBrowserServiceCompatApi24.sResultFlags.setInt(this.mResultObj, n);
                this.mResultObj.sendResult((Object)this.parcelListToItemList(list));
            }
            catch (IllegalAccessException ex) {
                Log.w("MBSCompatApi24", (Throwable)ex);
                continue;
            }
            break;
        }
    }
}
