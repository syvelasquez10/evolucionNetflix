// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.media.MediaRouter$Callback;
import android.media.MediaRouter;
import android.annotation.TargetApi;

@TargetApi(18)
final class MediaRouterJellybeanMr2
{
    public static void addCallback(final Object o, final int n, final Object o2, final int n2) {
        ((MediaRouter)o).addCallback(n, (MediaRouter$Callback)o2, n2);
    }
    
    public static Object getDefaultRoute(final Object o) {
        return ((MediaRouter)o).getDefaultRoute();
    }
}
