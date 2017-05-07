// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.util.Set;
import android.media.MediaMetadata;
import android.graphics.Bitmap;

class MediaMetadataCompatApi21
{
    public static Bitmap getBitmap(final Object o, final String s) {
        return ((MediaMetadata)o).getBitmap(s);
    }
    
    public static long getLong(final Object o, final String s) {
        return ((MediaMetadata)o).getLong(s);
    }
    
    public static Object getRating(final Object o, final String s) {
        return ((MediaMetadata)o).getRating(s);
    }
    
    public static CharSequence getText(final Object o, final String s) {
        return ((MediaMetadata)o).getText(s);
    }
    
    public static Set<String> keySet(final Object o) {
        return (Set<String>)((MediaMetadata)o).keySet();
    }
}
