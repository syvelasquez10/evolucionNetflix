// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.service.media.MediaBrowserService;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.service.media.MediaBrowserService$Result;
import java.lang.reflect.Field;

class MediaBrowserServiceCompatApi24
{
    private static final String TAG = "MBSCompatApi24";
    private static Field sResultFlags;
    
    static {
        try {
            (MediaBrowserServiceCompatApi24.sResultFlags = MediaBrowserService$Result.class.getDeclaredField("mFlags")).setAccessible(true);
        }
        catch (NoSuchFieldException ex) {
            Log.w("MBSCompatApi24", (Throwable)ex);
        }
    }
    
    public static Object createService(final Context context, final MediaBrowserServiceCompatApi24$ServiceCompatProxy mediaBrowserServiceCompatApi24$ServiceCompatProxy) {
        return new MediaBrowserServiceCompatApi24$MediaBrowserServiceAdaptor(context, mediaBrowserServiceCompatApi24$ServiceCompatProxy);
    }
    
    public static Bundle getBrowserRootHints(final Object o) {
        return ((MediaBrowserService)o).getBrowserRootHints();
    }
    
    public static void notifyChildrenChanged(final Object o, final String s, final Bundle bundle) {
        ((MediaBrowserService)o).notifyChildrenChanged(s, bundle);
    }
}
