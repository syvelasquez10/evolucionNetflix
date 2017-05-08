// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.media.MediaDescription;
import android.net.Uri;
import android.annotation.TargetApi;

@TargetApi(23)
class MediaDescriptionCompatApi23 extends MediaDescriptionCompatApi21
{
    public static Uri getMediaUri(final Object o) {
        return ((MediaDescription)o).getMediaUri();
    }
}
